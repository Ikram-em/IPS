package giis.demo.tkrun.tienda;

import java.util.List;
import java.util.Properties;

import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailSender {

    private final String senderEmail;
    private final String senderPassword;
    private final String smtpHost;
    private final int smtpPort;

    public EmailSender(String senderEmail, String senderPassword, String smtpHost, int smtpPort) {
        this.senderEmail = senderEmail;
        this.senderPassword = senderPassword;
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }

    public void sendPurchaseEmail(String recipientEmail, List<UserProductViewDTO> products, double total)
            throws MessagingException {

        // Configuración del servidor SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", String.valueOf(smtpPort));

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

        // Asunto del correo
        message.setSubject("Resumen de tu compra en la Tienda Oficial del Real Oviedo");

        // Contenido HTML
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("<html>");
        emailContent.append("<body style='font-family: Arial, sans-serif; color: #333;'>");

        // Logo Real Oviedo (si existe en resources)
        emailContent.append(
            "<div style='text-align:center; margin-bottom:20px;'>" +
            "<img src='cid:logoRO' style='width:120px;'>" +
            "</div>"
        );

        // Título
        emailContent.append("<h2 style='color: #0033A0; text-align:center;'>");
        emailContent.append("¡Gracias por tu compra en la Tienda Oficial del Real Oviedo!");
        emailContent.append("</h2>");

        emailContent.append("<p>Aquí tienes el resumen de tu pedido:</p>");

        // Tabla de productos
        emailContent.append("<table style='border-collapse: collapse; width: 100%;'>");
        emailContent.append("<tr>")
            .append("<th style='border: 1px solid #ddd; padding: 8px;'>Producto</th>")
            .append("<th style='border: 1px solid #ddd; padding: 8px;'>Cantidad</th>")
            .append("<th style='border: 1px solid #ddd; padding: 8px;'>Precio</th>")
            .append("<th style='border: 1px solid #ddd; padding: 8px;'>Subtotal</th>")
            .append("</tr>");

        for (UserProductViewDTO item : products) {
            double subtotal = item.getProduct().getPrecio() * item.getQuantity();

            emailContent.append("<tr>")
                .append("<td style='border: 1px solid #ddd; padding: 8px;'>")
                .append(item.getProduct().getNombre())
                .append("</td>")
                .append("<td style='border: 1px solid #ddd; padding: 8px; text-align: center;'>")
                .append(item.getQuantity())
                .append("</td>")
                .append("<td style='border: 1px solid #ddd; padding: 8px;'>")
                .append(String.format("%.2f €", item.getProduct().getPrecio()))
                .append("</td>")
                .append("<td style='border: 1px solid #ddd; padding: 8px;'>")
                .append(String.format("%.2f €", subtotal))
                .append("</td>")
                .append("</tr>");
        }

        // Total compra
        emailContent.append("<tr>")
            .append("<td colspan='3' style='border: 1px solid #ddd; padding: 8px; text-align: right;'><strong>Total:</strong></td>")
            .append("<td style='border: 1px solid #ddd; padding: 8px;'><strong>")
            .append(String.format("%.2f €", total))
            .append("</strong></td>")
            .append("</tr>");

        emailContent.append("</table>");

        // Mensaje final
        emailContent.append("<p>¡Esperamos que disfrutes tus productos del Real Oviedo!</p>");
        emailContent.append("<p>Atentamente,<br><strong>Tienda Oficial Real Oviedo</strong></p>");

        emailContent.append("</body></html>");

        // Crear el multipart con imagen + HTML
        MimeMultipart multipart = new MimeMultipart("related");

        // Parte HTML
        BodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(emailContent.toString(), "text/html; charset=utf-8");
        multipart.addBodyPart(htmlPart);

        // Logo incrustado
        try {
            MimeBodyPart imagePart = new MimeBodyPart();
            imagePart.attachFile("src/main/resources/images/logo.png");
            imagePart.setContentID("<logoRO>");
            imagePart.setDisposition(MimeBodyPart.INLINE);
            multipart.addBodyPart(imagePart);
        } catch (Exception e) {
            System.out.println("⚠ No se pudo cargar el logo del Real Oviedo.");
        }

        message.setContent(multipart);

        // Enviar correo
        Transport.send(message);
        System.out.println("Correo enviado correctamente a " + recipientEmail);
    }
}
