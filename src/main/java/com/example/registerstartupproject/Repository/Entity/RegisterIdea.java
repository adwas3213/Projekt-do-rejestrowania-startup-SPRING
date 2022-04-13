package com.example.registerstartupproject.Repository.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterIdea {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    String review;

    LocalDateTime reviewSendingDate;
    @Enumerated(EnumType.ORDINAL)
    Status status;


}
