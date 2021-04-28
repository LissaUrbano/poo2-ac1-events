package com.facens.event.entities;

public class AttendantDTO {

    private Long id;
    private String name;
    private String email;
    private String balance;

    public AttendantDTO() {
    }

    public AttendantDTO(Long id, String name, String email, String balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    public AttendantDTO(Attendant participant) {
        this.name = participant.getName();
        this.email = participant.getEmail();
        this.balance = participant.getBalance();
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getBalance() {
        return balance;
    }
    public void setBalance(String balance) {
        this.balance = balance;
    }

}
