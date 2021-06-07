package com.facens.event.services;

import java.util.Optional;

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

    public Page<AttendDTO> getAttends(PageRequest pageRequest) {
        Page<Attend> list = attendRepository.findAttendeesPageble(pageRequest);
        return list.map( e -> new AttendDTO(e));
    }

    public AttendDTO insert(AttendDTO attendDTO) {
        validateEmailAttend(attendDTO);
        return new AttendDTO(attendRepository.save(new Attend(attendDTO)));
    }

    public void delete(Long id) {
        try {
            attendRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound);
        }
    }

    public AttendDTO getAttendDtoById(Long id) {
        return new AttendDTO(getAttendById(id));
    }

    public AttendDTO update(Long id, AttendDTO attendDTO) {
        Attend attend = getAttendById(id);
            
        //não seta valores Null que vieram do DTO
        if (attendDTO.getName() != null) {
            attend.setName(attendDTO.getName());
        }
        if (attendDTO.getEmail() != null) {
            validateEmailAttend(attendDTO);
            attend.setEmail(attendDTO.getEmail());
        }

        return new AttendDTO(attendRepository.save(attend));
    }
    
    // Validação do Email - Attend
    public void validateEmailAttend(AttendDTO attendDTO){
        Optional<Attend> admin = attendRepository.findAdminByEmail(attendDTO.getEmail());
        if(admin.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já possui attend com esse email");
        }
    }

    public Attend getAttendById(Long id) {
        Optional<Attend> op = attendRepository.findById(id);
        return op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound));
    }
}
