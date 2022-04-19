package com.example.registerstartupproject.adminPanel;

import com.example.registerstartupproject.entity.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IdeaFromAdminDTO {

    private String review;

    private LocalDateTime reviewSendingDate;

    private Status status;
}
