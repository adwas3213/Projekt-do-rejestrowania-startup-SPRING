package com.example.registerstartupproject.registerAndEmailValidate;

import com.example.registerstartupproject.Repository.Entity.RegisterTeam;
import com.example.registerstartupproject.Repository.Entity.TokenToRegistry;
import com.example.registerstartupproject.registerAndEmailValidate.DTO.ContactDTO;
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

    private final String supportEmail;

    public EmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender,
                        @Value("${frontEndLink}") String frontEndLink, @Value("${emailSupport}") String supportEmail) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.frontEndLink = frontEndLink;
        this.supportEmail = supportEmail;
    }

    public void sendMail(RegisterDtoOuter user, TokenToRegistry token) throws MessagingException {
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

    }
    public void sendMail(RegisterTeam user) throws MessagingException {
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

    }
    public void sendMail(ContactDTO contactDTO) throws MessagingException {
        Context context = new Context();
        context.setVariable("userWithMessage", contactDTO);


        String process = templateEngine.process("emails/contactSupportMail.html", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setSubject(contactDTO.getName()+" zostawił wiadomość");
        helper.setText(process, true);
        System.out.println(supportEmail);
        helper.setTo(supportEmail);
        javaMailSender.send(mimeMessage);

    }
}