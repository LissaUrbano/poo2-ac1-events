package com.facens.event.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import com.facens.event.dto.EventDTO;
import com.facens.event.entities.Event;
import com.facens.event.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    private String msgNotFound = "Event not found";

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

}
