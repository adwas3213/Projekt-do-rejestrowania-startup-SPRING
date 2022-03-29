package com.example.registerstartupproject.registerAndEmailValidate;

import com.example.registerstartupproject.Repository.Entity.RegisterTeam;
import com.example.registerstartupproject.Repository.Entity.TokenToRegistry;
import com.example.registerstartupproject.registerAndEmailValidate.DTO.RegisterDtoOuter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

@Service
public class EmailService {

    private final TemplateEngine templateEngine;

    private final JavaMailSender javaMailSender;

    private final String frontEndLink;

    public EmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender, @Value("${frontEndLink}") String frontEndLink) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.frontEndLink = frontEndLink;
    }

    public String sendMail(RegisterDtoOuter user, TokenToRegistry token) throws MessagingException {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("validateLink", frontEndLink + "/validate?token=" + token.getToken());


        String process = templateEngine.process("emails/registerConfirmationEmail.html", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setSubject("Witaj w konkursie Elevator pitch !");
        helper.setText(process, true);
        helper.setTo(user.getEmail());
        javaMailSender.send(mimeMessage);
        return "Sent";
    }
    public String sendMail(RegisterTeam user) throws MessagingException {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("validateLink", frontEndLink + "/validate?token=" + user.getTokenToRegistry().getToken());


        String process = templateEngine.process("emails/registerConfirmationEmail.html", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setSubject("Witaj w konkursie Elevator pitch !");
        helper.setText(process, true);
        helper.setTo(user.getEmail());
        javaMailSender.send(mimeMessage);
        return "Sent";
    }
}