package com.example.security.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "test_results")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TestResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Date dateCompletionAnalysis;

    @Column(name = "hemoglobin")
    private double hemoglobinLevel;

    @Column(name = "leukocyte")
    private double leukocyteLevel;


}
