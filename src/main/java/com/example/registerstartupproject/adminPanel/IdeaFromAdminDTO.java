package com.example.registerstartupproject.adminPanel;

import com.example.registerstartupproject.Repository.Entity.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IdeaFromAdminDTO {

    private String content;

    private LocalDateTime contentSendingDate;
    private String review;

    private LocalDateTime reviewSendingDate;

    private Status status;
}
