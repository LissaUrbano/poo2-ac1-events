package com.facens.event.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.facens.event.dto.AttendDTO;

@Entity
@Table(name="TB_ATTEND")
@PrimaryKeyJoinColumn(name = "USERBASE_ID")
public class Attend extends BaseUser {
    
    private Double balance;

    @OneToMany
    @JoinColumn(name = "ATTEND_USERBASE_ID")
    private List<Ticket> tickets = new ArrayList<>(); 

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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTickets(Ticket ticket) {
        this.tickets.add(ticket);
    }
}
