package com.example.registerstartupproject.userConnectedAcctionsExceptLogin.InsertIdeaByUser;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class InsertDTOInner {
    String content;
    String email;
    LocalDateTime dateOfCreation;

}
