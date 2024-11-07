package co.edu.uniquindio.ProyectoFinalp3.controllers;

import co.edu.uniquindio.ProyectoFinalp3.models.Mensaje;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Método para enviar mensaje a un destinatario específico
    @MessageMapping("/chat.send")
    public void enviarMensaje(Mensaje mensaje) {
        // Enviar mensaje al destinatario
        messagingTemplate.convertAndSend("/topic/messages/" + mensaje.getDestinatarioId(), mensaje);
    }
}

