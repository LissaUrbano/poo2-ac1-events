package com.facens.event.entities;

import com.facens.event.dto.AdminDTO;

public class Admin extends BaseUser{

    private String phoneNumber;

    public Admin() {
    }

    public Admin(AdminDTO adminDTO) {
        super(adminDTO.getName(), adminDTO.getEmail());
        this.phoneNumber = adminDTO.getPhoneNumber();
	}

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
