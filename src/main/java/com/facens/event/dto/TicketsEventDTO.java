package com.facens.event.dto;

import java.util.ArrayList;
import java.util.List;

import com.facens.event.entities.Event;

public class TicketsEventDTO {

    private List<TicketAttendDTO> ticketsAttend = new ArrayList<>();
    private Long amountFreeTickets; 
    private Long amountPayedTickets;
    private Integer freeTickectsSelled;
    private Integer payedTickectsSelled;

    public TicketsEventDTO(Event event) {
        setAmountFreeTickets(event.getAmountFreeTickets());
        setAmountPayedTickets(event.getAmountPayedTickets());
        setFreeTickectsSelled(event.getFreeTickectsSelled());
        setPayedTickectsSelled(event.getPayedTickectsSelled());
    }

    public List<TicketAttendDTO> getTicketsAttend() {
        return ticketsAttend;
    }
    public void addTicketsAttend(TicketAttendDTO ticketsAttend) {
        this.ticketsAttend.add(ticketsAttend);
    }
    public Long getAmountFreeTickets() {
        return amountFreeTickets;
    }
    public void setAmountFreeTickets(Long amountFreeTickets) {
        this.amountFreeTickets = amountFreeTickets;
    }
    public Long getAmountPayedTickets() {
        return amountPayedTickets;
    }
    public void setAmountPayedTickets(Long amountPayedTickets) {
        this.amountPayedTickets = amountPayedTickets;
    }
    public Integer getFreeTickectsSelled() {
        return freeTickectsSelled;
    }
    public void setFreeTickectsSelled(Integer freeTickectsSelled) {
        this.freeTickectsSelled = freeTickectsSelled;
    }
    public Integer getPayedTickectsSelled() {
        return payedTickectsSelled;
    }
    public void setPayedTickectsSelled(Integer payedTickectsSelled) {
        this.payedTickectsSelled = payedTickectsSelled;
    }
}
