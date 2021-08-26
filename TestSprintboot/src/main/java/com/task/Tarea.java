package com.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Component
public class Tarea {
    @Autowired
    private JavaMailSender mailSender;

    /*Tareas Programadas
    @Scheduled(fixedRate = 1000)
    public void tarea(){
        System.out.println("Tarea programada");
    }*/

    //Tarea programada con Cron mas informacion https://riptutorial.com/spring/example/21209/cron-expression
    @Scheduled(cron = "*/5 * * * * *" )
    public void tareaCron() throws MessagingException, UnsupportedEncodingException {
        try {
            String[] Usuario = {"Daniel Gustavo Alzate ", "dalzateur@uninpahu.edu.co"};
            String[] Productos = {"1", "Frijoles", "500000", "10000", "6000000000"};
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            // Establecer el remitente (el segundo parámetro establece el apodo, que puede dejarse sin configurar)
            helper.setFrom("daniel.alzate@analitica.com.co", "Daniel Trabajo");
            // Establecer tema
            helper.setSubject("Ventas De Servicios");
            // Establezca el cuerpo (el segundo parámetro se establece en verdadero para escribir código HTML en el primer parámetro; de lo contrario, se analiza en texto normal)
            helper.setText("<h1 style='color: #654321;text-align: center;'>Buenas Tardes Señor "+ Usuario[0] + "</h1><p>" +
                    "<br><br> se le informa que la venta de " + Productos[1] + " Fue exitosa para mas informacion del producto se visualizara en la siguiente tabla " +
                    " <table><tr style='background-color:#99DADE'><th>IdProducto</th><th>Nombre Producto</th><th>Cantidad Vendida</th><th>Precio Unitario</th><th>Valor Total</th></tr>" +
                    "<tr><td>" + Productos[0] + "</td><td>" + Productos[1] + "</td><td>" + Productos[2] + "</td><td>" + Productos[3] + "</td><td>" + Productos[4] + "</td></tr></table> </p>", true);
            // Establezca el destinatario (también puede pasar una matriz String [] para enviar a múltiples destinos)
            helper.setTo(Usuario[1]);
            mailSender.send(message);
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println("Tarea programada Cron");
    }
}
