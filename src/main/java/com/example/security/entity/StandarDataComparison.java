package com.example.security.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "standard_data_comparison",
        uniqueConstraints = @UniqueConstraint(columnNames={"indicator", "gender", "age"}))
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StandarDataComparison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "indicator")
    private String indicator;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private int age;

    @Column(name = "standard_min")
    private double min;

    @Column(name = "standard_max")
    private double max;

}
