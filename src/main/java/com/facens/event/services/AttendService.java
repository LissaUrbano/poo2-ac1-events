package com.facens.event.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.facens.event.dto.AttendDTO;
import com.facens.event.entities.Attend;
import com.facens.event.repositories.AttendRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AttendService {

    @Autowired
    private AttendRepository attendRepository;

    private String msgNotFound = "Attend not found";

    public Page<AttendDTO> getAttends(PageRequest pageRequest, String name, String email) {
        Page<Attend> list = attendRepository.findAttendeesPageble(pageRequest, name, email);
        return list.map( e -> new AttendDTO(e));
    }

    public AttendDTO insert(AttendDTO attendDTO) {
        Attend attend = new Attend(attendDTO);
        return new AttendDTO(attendRepository.save(attend));
    }

    public void delete(Long id) {
        try {
            attendRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound);
        }
    }

    public AttendDTO getAttendById(Long id) {
        Optional<Attend> op = attendRepository.findById(id);
        Attend attend = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound));
        return new AttendDTO(attend);
    }

    public AttendDTO update(Long id, AttendDTO attendDTO) { 
        try {
            Attend attend = attendRepository.getOne(id);
            //não seta valores Null que vieram do DTO
            if(attendDTO.getName() != null) {
                attend.setName(attendDTO.getName());
            }
            if(attendDTO.getEmail() != null) {
                attend.setEmail(attendDTO.getEmail());
            }
            
            attend = attendRepository.save(attend);
            return new AttendDTO(attend);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound);
        }
    }
}
