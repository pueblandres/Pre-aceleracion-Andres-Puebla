package com.Alkemy.demo.servicios.impl;

import com.Alkemy.demo.excepcion.ErrorServicio;
import com.Alkemy.demo.servicios.EmailServicio;
import com.sendgrid.Method;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import com.sendgrid.Request;

@Service
public class EmailServicioImpl implements EmailServicio {

    @Autowired
    private Environment env;

    @Value("${alkemy.demo.email.sender}")
    private String emailSender;


    public void sendWelcomeEmailTo(String to) {
        String apiKey = env.getProperty("EMAIL_API_KEY");

        Email fromEmail = new Email(emailSender);
        Email toEmail = new Email(to);
        Content content = new Content(
                "text/plain",
                "Bienvenido/a al Challenge de Alkemy Disney API"
        );
        String subject = "Alkemy Challenge";

        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

        } catch (IOException ex) {
           throw new ErrorServicio(ex.getMessage());
        }

    }
}
