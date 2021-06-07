package com.facens.event.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.facens.event.entities.Attend;
import com.facens.event.entities.Ticket;

public class AttendDTO {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "E-mail is mandatory")
    private String email;
    
    private Double balance;

    private List<Ticket> tickets = new ArrayList<>(); 

    public AttendDTO() {
    }

    public AttendDTO(Long id, String name, String email, Double balance, List<Ticket> tickets) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.tickets = tickets;
    }

    public AttendDTO(Attend attend) {
        setId(attend.getId());
        setName(attend.getName());
        setEmail(attend.getEmail());
        setBalance(attend.getBalance());
        setTickets(attend.getTickets());
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
    public List<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
