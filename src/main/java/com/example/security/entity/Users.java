package com.example.security.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "last_diagnosis")
    private Long lastDiagnosis;

    @Column(name = "contact")
    private String contact;

    @Column(name = "activation_code")
    private String activationCode;




    @ManyToMany(fetch = FetchType.EAGER)
    private List<Roles> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(this.activationCode == null) return true;

        return false;

    }

    public Users(Long id, String email, String password, String fullName, Date birthDate, String gender, Long lastDiagnosis, String contact) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.lastDiagnosis = lastDiagnosis;
        this.contact = contact;
    }

    public Users(Long id, String email, String fullName, Date birthDate, String gender, Long lastDiagnosis, String contact, List<Roles> roles) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.lastDiagnosis = lastDiagnosis;
        this.contact = contact;
        this.roles = roles;
    }
}
