package com.facens.event.dto;

import javax.validation.constraints.NotBlank;

import com.facens.event.entities.Attend;

public class AttendDTO {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "E-mail is mandatory")
    private String email;
    
    private Double balance;

    public AttendDTO() {
    }

    public AttendDTO(Long id, String name, String email, Double balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    public AttendDTO(Attend attend) {
        setId(attend.getId());
        setName(attend.getName());
        setEmail(attend.getEmail());
        setBalance(attend.getBalance());
	}

    public Long getId() {
        return id;
    }
    private void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
