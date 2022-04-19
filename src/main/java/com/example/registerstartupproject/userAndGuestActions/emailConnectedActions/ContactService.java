package com.example.registerstartupproject.userAndGuestActions.emailConnectedActions;

import com.example.registerstartupproject.userAndGuestActions.DTO.ContactDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final EmailService emailService;
    public void sendContactMessage(ContactDTO contactDTO) {
        try {
            emailService.sendMail(contactDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
