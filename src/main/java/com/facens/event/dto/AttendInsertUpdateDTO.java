package com.facens.event.dto;

import javax.validation.constraints.NotBlank;

import com.facens.event.entities.Attend;

public class AttendInsertUpdateDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "E-mail is mandatory")
    private String email;

    public AttendInsertUpdateDTO() {
    }

    public AttendInsertUpdateDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public AttendInsertUpdateDTO(Attend attend) {
        setName(attend.getName());
        setEmail(attend.getEmail());
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

}
