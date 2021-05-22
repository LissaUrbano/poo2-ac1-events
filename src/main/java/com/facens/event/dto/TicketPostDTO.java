package com.facens.event.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotNull;

import com.facens.event.entities.Ticket;
import com.facens.event.entities.TypeTicket;

public class TicketPostDTO implements Serializable{

    private Long id;

    @NotNull(message = "Type is mandatory")
    private TypeTicket type;

    private Instant date;

    private Double price;

    @NotNull(message = "Attend Id is mandatory")
    private Long attend;

    public TicketPostDTO() {
    }

    public TicketPostDTO(TypeTicket type, Instant date, Double price) {
        this.type = type;
        this.date = date;
        this.price = price;
    }

    public TicketPostDTO(Ticket ticket) {
        setId(ticket.getId());
        setType(ticket.getType());
        setDate(ticket.getDate());
        setPrice(ticket.getPrice());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeTicket getType() {
        return type;
    }

    public void setType(TypeTicket type) {
        this.type = type;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getAttend() {
        return attend;
    }

    public void setAttend(Long attend) {
        this.attend = attend;
    }
}
