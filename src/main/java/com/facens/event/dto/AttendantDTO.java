package com.facens.event.dto;

import com.facens.event.entities.Attendant;

public class AttendantDTO {

    private Long id;
    private String name;
    private String email;
    private String balance;

    public AttendantDTO() {
    }

    public AttendantDTO(Long id, String name, String email, String balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    public AttendantDTO(Attendant attendant) {
        setId(attendant.getId());
        setName(attendant.getName());
        setEmail(attendant.getEmail());
        setBalance(attendant.getBalance());
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
    public String getBalance() {
        return balance;
    }
    public void setBalance(String balance) {
        this.balance = balance;
    }

}
