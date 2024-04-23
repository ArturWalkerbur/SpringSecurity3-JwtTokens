package com.example.security.entity;
import jakarta.persistence.*;
import lombok.*;


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
    private double rating;

    @Column(name = "comments", columnDefinition = "text")
    private String comments;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users user;

    public Assessment(Long id, double rating, String comments) {
        this.id = id;
        this.rating = rating;
        this.comments = comments;
    }
}
