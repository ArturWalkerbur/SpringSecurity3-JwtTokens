package com.example.security.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestResults_dto {

    private Date dateCompletionAnalysis;
    private double hemoglobinLevel;
    private double leukocyteLevel;


}
