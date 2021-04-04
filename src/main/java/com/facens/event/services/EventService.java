package com.facens.event.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.facens.event.dto.EventDTO;
import com.facens.event.entities.Event;
import com.facens.event.repositories.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    private String msgNotFound = "Event not found";

    public Page<EventDTO> getEvents(PageRequest pageRequest, String name, String place, String startDate, String description) {
        LocalDate date = convertLocalDate(startDate);
        Page<Event> list = eventRepository.findEventsPageble(pageRequest, name, place, date, description);
        return list.map( e -> new EventDTO(e));
    }

    public EventDTO insert(EventDTO eventDTO) {
        validarDataHora(eventDTO);
        Event event = new Event(eventDTO);
        return new EventDTO(eventRepository.save(event));
    }

    public void delete(Long id) {
        try {
            eventRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound);
        }
    }

    public EventDTO getEventById(Long id) {
        Optional<Event> op = eventRepository.findById(id);
        Event event = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound));
        return new EventDTO(event);
    }

    public EventDTO update(Long id, EventDTO eventDTO) { 
        try {
            Event event = eventRepository.getOne(id);
            //não seta valores Null que vieram do DTO
            if(eventDTO.getName() != null) {
                event.setName(eventDTO.getName());
            }
            if(eventDTO.getDescription() != null) {
                event.setDescription(eventDTO.getDescription());
            }
            if(eventDTO.getPlace() != null) {
                event.setPlace(eventDTO.getPlace());
            }
            if(eventDTO.getStartDate() != null) {
                event.setStartDate(eventDTO.getStartDate()); 
            }
            if(eventDTO.getEndDate() != null) {
                event.setEndDate(eventDTO.getEndDate());
            }
            if(eventDTO.getStartTime() != null) {
                event.setStartTime(eventDTO.getStartTime());
            }
            if(eventDTO.getEndTime() != null) {
                event.setEndTime(eventDTO.getEndTime()); 
            }
            if(eventDTO.getEmail() != null) {
                event.setEmail(eventDTO.getEmail());
            }

            //valida os novos dados inseridos para Data e Hora
            EventDTO eventValidacao = new EventDTO(event);
            validarDataHora(eventValidacao);
            
            event = eventRepository.save(event);
            return new EventDTO(event);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound);
        }
    }
    
    private void validarDataHora(EventDTO eventDTO) {

        if (eventDTO.getStartDate().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data inicial informada deve ser igual ou maior que a data de hoje");
        }
        if (eventDTO.getEndDate().isBefore(eventDTO.getStartDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data final informada deve ser igual ou maior que a data inicial do evento");
        }
        if (eventDTO.getStartDate().isEqual(LocalDate.now()) && eventDTO.getStartTime().isBefore(LocalTime.now()) ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Para eventos na data de hoje a hora inicial deve ser igual ou maior ao horario atual");
        }
        if (eventDTO.getEndTime().isBefore(eventDTO.getStartTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A hora final informada deve ser maior que a hora inicial do evento");
        }
    }

    private LocalDate convertLocalDate(String startDate) {
        if (!startDate.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");    
            try {
                LocalDate date = LocalDate.parse(startDate, formatter);
                return date;
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de filtro inválida, inserir no formato dia/mês/ano. exemplo: 03/04/2021");
            }  
        } else {
            return LocalDate.of(2000, 01, 01) ; //date default caso null
        }     
    }

}
