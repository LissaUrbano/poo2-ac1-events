package com.facens.event.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.facens.event.entities.Event;
import com.facens.event.entities.Place;

public class EventDTO {
    
    private Long id;
    private String name;
    private String description;
    private List<Place> place;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String emailContact;

    private Long amountFreeTickets; 
    private Long amountPayedTickets;
    private Long freeTickectsSelled;
    private Long payedTickectsSelled;
    private Double priceTicket;
    
    public EventDTO() {
    }

    public EventDTO(Long id, String name, String description, List<Place> place, LocalDate startDate, LocalDate endDate,
            LocalTime startTime, LocalTime endTime, String emailContact, Long amountFreeTickets, Long amountPayedTickets,
            Long freeTickectsSelled, Long payedTickectsSelled, Double priceTicket) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.place = place;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.emailContact = emailContact;
        this.amountFreeTickets = amountFreeTickets;
        this.amountPayedTickets = amountPayedTickets;
        this.freeTickectsSelled = freeTickectsSelled;
        this.payedTickectsSelled = payedTickectsSelled;
        this.priceTicket = priceTicket;
    }

    public EventDTO(Event event) {
        setId(event.getId());
        setName(event.getName());
        setDescription(event.getDescription());
        setPlace(event.getPlace());
        setStartDate(event.getStartDate());
        setEndDate(event.getEndDate());
        setStartTime(event.getStartTime());
        setEndTime(event.getEndTime());
        setEmailContact(event.getEmailContact());
        setAmountFreeTickets(event.getAmountFreeTickets());
        setAmountPayedTickets(event.getAmountPayedTickets());
        setFreeTickectsSelled(event.getFreeTickectsSelled());
        setPayedTickectsSelled(event.getPayedTickectsSelled());
        setPriceTicket(event.getPriceTicket());
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Place> getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place.add(place);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getEmailContact() {
        return emailContact;
    }

    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
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

    public Long getFreeTickectsSelled() {
        return freeTickectsSelled;
    }

    public void setFreeTickectsSelled(Long freeTickectsSelled) {
        this.freeTickectsSelled = freeTickectsSelled;
    }

    public Long getPayedTickectsSelled() {
        return payedTickectsSelled;
    }

    public void setPayedTickectsSelled(Long payedTickectsSelled) {
        this.payedTickectsSelled = payedTickectsSelled;
    }

    public Double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(Double priceTicket) {
        this.priceTicket = priceTicket;
    }
}

    