package com.facens.event.entities;

import com.facens.event.dto.AttendDTO;

public class Attend extends BaseUser{
    
    private Double balance;

    public Attend() {
    }

    public Attend(AttendDTO attendDTO) {
        super(attendDTO.getName(), attendDTO.getEmail());
        this.balance = attendDTO.getBalance();
	}

    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
}
