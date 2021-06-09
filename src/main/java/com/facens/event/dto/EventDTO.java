package com.facens.event.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.facens.event.entities.Event;
import com.facens.event.entities.Place;

public class EventDTO {
    
    private Long id;

    @NotBlank(message = "Address is mandatory")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotNull(message = "Start Date is mandatory")
    private LocalDate startDate;

    @NotNull(message = "End Date is mandatory")
    private LocalDate endDate;

    @NotNull(message = "Start Time is mandatory")
    private LocalTime startTime;

    @NotNull(message = "End Time is mandatory")
    private LocalTime endTime;

    @Email
    @NotBlank(message = "E-mail contact is mandatory")
    private String emailContact;

    @NotNull(message = "Amount Free Tickets is mandatory")
    private Long amountFreeTickets; 

    @NotNull(message = "Amount Payed Tickets is mandatory")
    private Long amountPayedTickets;

    private Integer freeTickectsSelled;
    private Integer payedTickectsSelled;

    @NotNull(message = "Price Ticket is mandatory")
    private Double priceTicket;
    
    private List<PlaceDTO> places = new ArrayList<>(); 

    //private List<Ticket> tickets = new ArrayList<>(); 

    @NotNull(message = "Admin Id is mandatory")
    private Long admin;
    
    public EventDTO() {
    }

    /*
    public EventDTO(Long id, String name, String description, List<Place> places, LocalDate startDate, LocalDate endDate,
            LocalTime startTime, LocalTime endTime, String emailContact, Long amountFreeTickets, Long amountPayedTickets,
            Integer freeTickectsSelled, Integer payedTickectsSelled, Double priceTicket, Long admin, List<Ticket> tickets) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.places = places;
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
        this.admin= admin;
        this.tickets = tickets;
    }*/

    public EventDTO(Event event) {
        setId(event.getId());
        setName(event.getName());
        setDescription(event.getDescription());
        setPlaces(convertListPlaceDTO(event.getPlaces()));
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
        setAdmin(event.getAdmin().getId());
        //setTickets(event.getTickets());
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

    public Double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(Double priceTicket) {
        this.priceTicket = priceTicket;
    }

    public List<PlaceDTO> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlaceDTO> places) {
        this.places = places;
    }

    public Long getAdmin() {
        return admin;
    }

    public void setAdmin(Long admin) {
        this.admin = admin;
    }

    private List<PlaceDTO> convertListPlaceDTO(List<Place> places2) {
        List<PlaceDTO> listPlaceDTO = new ArrayList<>();
        for (Place place: places2) {
            PlaceDTO placeDTO = new PlaceDTO(place);
            listPlaceDTO.add(placeDTO);
        }
        return listPlaceDTO;
    }

    /*
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }*/
}

    