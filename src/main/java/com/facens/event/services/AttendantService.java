package com.facens.event.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.facens.event.dto.AttendantDTO;
import com.facens.event.entities.Attendant;
import com.facens.event.repositories.AttendantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AttendantService {

    @Autowired
    private AttendantRepository attendantRepository;

    private String msgNotFound = "Attendant not found";

    public Page<AttendantDTO> getAttendants(PageRequest pageRequest, String name, String email) {
        Page<Attendant> list = attendantRepository.findAttendeesPageble(pageRequest, name, email);
        return list.map( e -> new AttendantDTO(e));
    }

    public AttendantDTO insert(AttendantDTO attendantDTO) {
        Attendant attendant = new Attendant(attendantDTO);
        return new AttendantDTO(attendantRepository.save(attendant));
    }

    public void delete(Long id) {
        try {
            attendantRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound);
        }
    }

    public AttendantDTO getAttendantById(Long id) {
        Optional<Attendant> op = attendantRepository.findById(id);
        Attendant attendant = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound));
        return new AttendantDTO(attendant);
    }

    public AttendantDTO update(Long id, AttendantDTO attendantDTO) { 
        try {
            Attendant attendant = attendantRepository.getOne(id);
            //não seta valores Null que vieram do DTO
            if(attendantDTO.getName() != null) {
                attendant.setName(attendantDTO.getName());
            }
            if(attendantDTO.getEmail() != null) {
                attendant.setEmail(attendantDTO.getEmail());
            }
            
            attendant = attendantRepository.save(attendant);
            return new AttendantDTO(attendant);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound);
        }
    }
}
