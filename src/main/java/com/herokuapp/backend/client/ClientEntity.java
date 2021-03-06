package com.herokuapp.backend.client;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Column(name = "password_reset_token")
    private String passwordResetToken;

    @Column(name = "password_reset_token_active")
    private Boolean passwordResetTokenActive;

    @Column(name = "password_reset_token_validity")
    private LocalDateTime passwordResetTokenValidity;

    public ClientEntity() {
    }

    public ClientEntity(Long id, String email, String name, String surname) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public Boolean getPasswordResetTokenActive() {
        return passwordResetTokenActive;
    }

    public void setPasswordResetTokenActive(Boolean passwordResetTokenActive) {
        this.passwordResetTokenActive = passwordResetTokenActive;
    }

    public LocalDateTime getPasswordResetTokenValidity() {
        return passwordResetTokenValidity;
    }

    public void setPasswordResetTokenValidity(LocalDateTime passwordResetTokenValidity) {
        this.passwordResetTokenValidity = passwordResetTokenValidity;
    }
}