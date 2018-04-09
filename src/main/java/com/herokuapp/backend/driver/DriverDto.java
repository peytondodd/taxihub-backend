package com.herokuapp.backend.driver;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class DriverDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private Long corporationId;


    public DriverDto(Long id, String name, String surname, String email, Long corporationId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.corporationId = corporationId;

    }

    public DriverDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCorporationIdid() {
        return corporationId;
    }

    public void setCorporationId(Long corporationId) {
        this.corporationId = corporationId;
    }
}