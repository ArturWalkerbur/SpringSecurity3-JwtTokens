package com.example.security.entity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "assessment")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rating")
    private String rating;

    @Column(name = "comments")
    private String comments;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users user;

}
