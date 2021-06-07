package com.facens.event.services;

import java.util.Optional;

import com.facens.event.dto.PlaceDTO;
import com.facens.event.entities.Place;
import com.facens.event.repositories.PlaceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    private String msgNotFound = "Place not found";

    public Page<PlaceDTO> getPlaces(PageRequest pageRequest, String name, String address) {
        Page<Place> list = placeRepository.findPlacesPageble(pageRequest, name, address);
        return list.map( e -> new PlaceDTO(e));
    }

    public PlaceDTO insert(PlaceDTO placeDTO) {
        return new PlaceDTO(placeRepository.save(new Place(placeDTO)));
    }

    public void delete(Long id) {
        try {
            placeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound);
        }
    }

    public PlaceDTO getPlaceDtoById(Long id) {
        return new PlaceDTO(getPlaceById(id));
    }

    public PlaceDTO update(Long id, PlaceDTO placeDTO) {
        Place place = getPlaceById(id);

        //não seta valores Null que vieram do DTO
        if(placeDTO.getName() != null) {
            place.setName(placeDTO.getName());
        }
        if(placeDTO.getAddress() != null) {
            place.setAddress(placeDTO.getAddress()); 
        }

        return new PlaceDTO(placeRepository.save(place));
    }

    public Place getPlaceById(Long id) {
        Optional<Place> op = placeRepository.findById(id);
        return op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound));
    }
}
