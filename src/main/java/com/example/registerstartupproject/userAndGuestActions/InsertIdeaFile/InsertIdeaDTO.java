package com.example.registerstartupproject.userAndGuestActions.InsertIdeaFile;

import lombok.Data;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.multipart.MultipartFile;

@Data

public class InsertIdeaDTO {
    final private MultipartFile idea;
    final private MultipartFile application;
    final private SecurityContext user;
}
