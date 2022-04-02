package com.example.registerstartupproject.Repository.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterIdea {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    String content;

    LocalDateTime contentSendingDate;


    String review;

    LocalDateTime reviewSendingDate;

}
