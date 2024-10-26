package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.util.ListaWrapper;
import co.edu.uniquindio.ProyectoFinalp3.models.*;
import co.edu.uniquindio.ProyectoFinalp3.repository.*; 
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BackupService {

    private final AdministradorRepository administradorRepository;
    private final MarketPlaceRepository marketPlaceRepository;
    private final VendedorRepository vendedorRepository;
    private final VentaRepository ventaRepository;

    public BackupService(AdministradorRepository administradorRepository, MarketPlaceRepository marketPlaceRepository, VendedorRepository vendedorRepository, VentaRepository ventaRepository) {
        this.administradorRepository = administradorRepository;
        this.marketPlaceRepository = marketPlaceRepository;
        this.vendedorRepository = vendedorRepository;
        this.ventaRepository = ventaRepository;
    }

    // Método programado para ejecutar el backup cada 30 minutos
    @Scheduled(fixedRate = 1800000) // 30 minutos
    public void scheduleBackup() {
        Thread backupThread = new Thread(() -> {
            try {
                backupEntities();
                System.out.println("Backup completado correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        backupThread.start();
    }

    private void backupEntities() throws Exception {
        // Definir el directorio de backups
        String backupDir = "C:/Users/Alejandro Polania/backups/";
    
        // Asegurarse de que el directorio exista
        try {
            Files.createDirectories(Paths.get(backupDir));
            System.out.println("Directorio de backup creado o ya existe: " + backupDir);
        } catch (Exception e) {
            System.err.println("Error creando el directorio de backups: " + e.getMessage());
            e.printStackTrace();
            return;  // Detener el proceso si no se puede crear el directorio
        }
    
        // Usar un formateador para el timestamp en los nombres de los archivos
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
    
        // Serializar los administradores
        List<Administrador> administradores = administradorRepository.findAll();
        serializeToDat(administradores, "administradores", backupDir, timestamp);
        serializeToXml(administradores, "administradores", backupDir, timestamp);
    
        // Serializar los MarketPlaces
        List<MarketPlace> marketPlaces = marketPlaceRepository.findAll();
        serializeToDat(marketPlaces, "marketplaces", backupDir, timestamp);
        serializeToXml(marketPlaces, "marketplaces", backupDir, timestamp);
    
        // Serializar los vendedores
        List<Vendedor> vendedores = vendedorRepository.findAll();
        serializeToDat(vendedores, "vendedores", backupDir, timestamp);
        serializeToXml(vendedores, "vendedores", backupDir, timestamp);
    
        // Serializar las ventas
        List<Venta> ventas = ventaRepository.findAll();
        serializeToDat(ventas, "ventas", backupDir, timestamp);
        serializeToXml(ventas, "ventas", backupDir, timestamp);
    }

    // Serialización en formato .dat
private void serializeToDat(Object data, String entityName, String backupDir, String timestamp) throws Exception {
    // Definir el nombre del archivo .dat
    String datFileName = backupDir + entityName + "_backup_" + timestamp + ".dat";
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(datFileName))) {
        oos.writeObject(data);
    }
}

private void serializeToXml(List<?> data, String entityName, String backupDir, String timestamp) throws Exception {
    // Definir el nombre del archivo .xml
    String xmlFileName = backupDir + entityName + "_backup_" + timestamp + ".xml";
    
    // Envuelve la lista en ListaWrapper
    ListaWrapper<?> wrapper = new ListaWrapper<>(data);

    JAXBContext context = JAXBContext.newInstance(ListaWrapper.class);
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

    try (FileOutputStream fos = new FileOutputStream(xmlFileName)) {
        marshaller.marshal(wrapper, fos);
    }
}

    // Método para deserializar .dat (restaurar los objetos)
    public Object deserializeFromDat(String datFileName) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(datFileName)))) {
            return ois.readObject();
        }
    }

    // Método para deserializar .xml (restaurar los objetos)
    public Object deserializeFromXml(String xmlFileName, Class<?> clazz) throws Exception {
        JAXBContext context = JAXBContext.newInstance(List.class, clazz);
        return context.createUnmarshaller().unmarshal(Files.newInputStream(Paths.get(xmlFileName)));
    }
}