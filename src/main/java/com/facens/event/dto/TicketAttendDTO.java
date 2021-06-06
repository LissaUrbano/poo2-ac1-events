package com.facens.event.dto;

import com.facens.event.entities.TypeTicket;

public class TicketAttendDTO {

    private Long id;
    private TypeTicket type;
    private String nameAttend;

    public TicketAttendDTO(){
        
    }

    public TicketAttendDTO(Long id, TypeTicket type, String nameAttend) {
        this.id = id;
        this.type = type;
        this.nameAttend = nameAttend;
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
    public String getNameAttend() {
        return nameAttend;
    }
    public void setNameAttend(String nameAttend) {
        this.nameAttend = nameAttend;
    }
}
