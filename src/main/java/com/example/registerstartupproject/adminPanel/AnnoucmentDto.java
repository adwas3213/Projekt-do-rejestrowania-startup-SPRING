package com.example.registerstartupproject.adminPanel;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AnnoucmentDto {
    @NotEmpty
    @NotNull
    private String topic;
    @NotEmpty
    @NotNull
    private String text;
}
