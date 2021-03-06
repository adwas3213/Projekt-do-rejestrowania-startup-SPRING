package com.example.registerstartupproject.userAndGuestActions.emailConnectedActions;

import com.example.registerstartupproject.entity.RegisterTeam;
import com.example.registerstartupproject.entity.TokenToRegistry;
import com.example.registerstartupproject.adminPanel.AnnoucmentDto;
import com.example.registerstartupproject.userAndGuestActions.DTO.ContactDTO;
import com.example.registerstartupproject.userAndGuestActions.DTO.RegisterDtoOuter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    private final TemplateEngine templateEngine;

    private final JavaMailSender javaMailSender;

    private final String frontEndLink;

    private final String supportEmail;

    private final String fromWhereEmailIsSend;

    public EmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender,
                        @Value("${frontEndLink}") String frontEndLink,
                        @Value("${emailSupport}") String supportEmail,
                        @Value("${spring.mail.username}") String fromWhereEmailIsSend) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.frontEndLink = frontEndLink;
        this.supportEmail = supportEmail;
        this.fromWhereEmailIsSend = fromWhereEmailIsSend;
    }
    public void sendMail(RegisterDtoOuter user, TokenToRegistry token) throws MessagingException, UnsupportedEncodingException {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("validateLink", frontEndLink + "/validate?token=" + token.getToken());
        String process = templateEngine.process("emails/registerConfirmationEmail.html", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Witaj w konkursie Elevator pitch !");
        helper.setText(process, true);
        helper.setTo(user.getEmail());
        helper.setFrom(fromWhereEmailIsSend, "Elevator Pitch WSEI");
        javaMailSender.send(mimeMessage);
    }

    public void sendMail(RegisterTeam user) throws MessagingException, UnsupportedEncodingException {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("validateLink", frontEndLink + "/validate?token=" + user.getTokenToRegistry().getToken());
        String process = templateEngine.process("emails/registerConfirmationEmail.html", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Witaj w konkursie Elevator pitch !");
        helper.setText(process, true);
        helper.setTo(user.getEmail());
        helper.setFrom(fromWhereEmailIsSend, "Elevator Pitch WSEI");
        javaMailSender.send(mimeMessage);
    }

    public void sendMail(ContactDTO contactDTO) throws MessagingException, UnsupportedEncodingException {
        Context context = new Context();
        context.setVariable("userWithMessage", contactDTO);
        String process = templateEngine.process("emails/contactSupportMail.html", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(contactDTO.getName() + " zostawi?? wiadomo????");
        helper.setText(process, true);
        helper.setTo(supportEmail);
        helper.setFrom(fromWhereEmailIsSend, "Elevator Pitch WSEI");
        javaMailSender.send(mimeMessage);
    }

    public void sendMail(AnnoucmentDto annoucment, String toWhomItWillBeSent, RegisterTeam registerTeam) throws MessagingException, UnsupportedEncodingException {
        Context context = new Context();
        context.setVariable("annoucment", annoucment);
        context.setVariable("team", registerTeam);
        context.setVariable("frontEndLink", frontEndLink);
        String process = templateEngine.process("emails/annoucment.html", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(annoucment.getTopic());
        helper.setText(process, true);
        helper.setTo(toWhomItWillBeSent);
        helper.setFrom(fromWhereEmailIsSend, "Elevator Pitch WSEI");
        helper.setFrom(new InternetAddress());
        javaMailSender.send(mimeMessage);

    }
}