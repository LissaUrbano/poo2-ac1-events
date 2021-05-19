package com.facens.event.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import com.facens.event.dto.AdminDTO;

@Entity
@Table(name="TB_ADMIN")
@PrimaryKeyJoinColumn(name = "USERBASE_ID")
public class Admin extends BaseUser {

    private String phoneNumber;

    @OneToMany(mappedBy = "admin")
    private List<Event> events = new ArrayList<>();

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

    public List<Event> getEvents() {
        return events;
    }

    public void addEvents(Event event) {
        this.events.add(event);
    }
}
