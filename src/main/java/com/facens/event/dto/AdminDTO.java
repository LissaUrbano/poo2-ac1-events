package com.facens.event.dto;

import javax.validation.constraints.NotBlank;

import com.facens.event.entities.Admin;

public class AdminDTO {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "E-mail is mandatory")
    private String email;

    @NotBlank(message = "Phone Number is mandatory")
    private String phoneNumber;
    
    public AdminDTO() {
    }

    public AdminDTO(Long id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public AdminDTO(Admin admin) {
        setId(admin.getId());
        setName(admin.getName());
        setEmail(admin.getEmail());
        setPhoneNumber(admin.getPhoneNumber());
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

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
