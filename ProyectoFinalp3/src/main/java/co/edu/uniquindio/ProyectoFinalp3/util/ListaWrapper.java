package co.edu.uniquindio.ProyectoFinalp3.util;


import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ListaWrapper<T> {
    private List<T> items;

    public ListaWrapper() {
    }

    public ListaWrapper(List<T> items) {
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}