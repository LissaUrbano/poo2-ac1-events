package com.facens.event.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.facens.event.dto.EventDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="TB_EVENT")
public class Event implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    //private String place;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;
    private String emailContact;

    private Integer amountFreeTickets; 
    private Integer amountPayedTickets;
    private Integer freeTickectsSelled;
    private Integer payedTickectsSelled;
    private Double priceTicket;

    public Event() {
    }

    public Event(EventDTO eventDTO) {
        this.name = eventDTO.getName();
        this.description = eventDTO.getDescription();
        //this.place = eventDTO.getPlace();
        this.startDate = eventDTO.getStartDate();
        this.endDate = eventDTO.getEndDate();
        this.startTime = eventDTO.getStartTime();
        this.endTime = eventDTO.getEndTime();
        this.emailContact = eventDTO.getEmailContact();
        this.amountFreeTickets = eventDTO.getAmountFreeTickets();
        this.amountPayedTickets = eventDTO.getAmountPayedTickets();
        this.freeTickectsSelled = eventDTO.getFreeTickectsSelled();
        this.payedTickectsSelled = eventDTO.getPayedTickectsSelled();
        this.priceTicket = eventDTO.getPriceTicket();
	}

    public Long getId() {
        return id;
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
    /*
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }*/
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


    

    public Integer getAmountFreeTickets() {
        return amountFreeTickets;
    }

    public void setAmountFreeTickets(Integer amountFreeTickets) {
        this.amountFreeTickets = amountFreeTickets;
    }

    public Integer getAmountPayedTickets() {
        return amountPayedTickets;
    }

    public void setAmountPayedTickets(Integer amountPayedTickets) {
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



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Event other = (Event) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
