package com.facens.event.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

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
        Place place = new Place(placeDTO);
        return new PlaceDTO(placeRepository.save(place));
    }

    public void delete(Long id) {
        try {
            placeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound);
        }
    }

    public PlaceDTO getPlaceById(Long id) {
        Optional<Place> op = placeRepository.findById(id);
        Place place = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound));
        return new PlaceDTO(place);
    }

    public PlaceDTO update(Long id, PlaceDTO placeDTO) { 
        try {
            Place place = placeRepository.getOne(id);
            //não seta valores Null que vieram do DTO
            if(placeDTO.getName() != null) {
                place.setName(placeDTO.getName());
            }
            if(placeDTO.getAddress() != null) {
                place.setAddress(placeDTO.getAddress()); 
            }
            
            place = placeRepository.save(place);
            return new PlaceDTO(place);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound);
        }
    }
}
