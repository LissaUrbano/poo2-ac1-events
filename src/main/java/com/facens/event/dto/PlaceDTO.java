package com.facens.event.dto;

import javax.validation.constraints.NotBlank;

import com.facens.event.entities.Place;

public class PlaceDTO {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Address is mandatory")
    private String address;

    public PlaceDTO() {
    }

    public PlaceDTO(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public PlaceDTO(Place place) {
        setId(place.getId());
        setName(place.getName());
        setAddress(place.getAddress());
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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
