package com.facens.event.services;

import java.time.LocalDate;

import com.facens.event.dto.EventDTO;
import com.facens.event.entities.Event;
import com.facens.event.repositories.EventRepository;
import com.facens.event.services.exception.DataFinalInvalidaException;
import com.facens.event.services.exception.DataInicialInvalidaException;
import com.facens.event.services.exception.HoraFinalInvalidaException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public EventDTO insert(EventDTO eventDTO) {
        Event event = new Event(eventDTO);
        validarDataHora(event);
        return new EventDTO(eventRepository.save(event));
    }

    
    private void validarDataHora(Event event) {

        if (event.getStartDate().isBefore(LocalDate.now())) {
            throw new DataInicialInvalidaException();
        }
        if (event.getEndDate().isBefore(event.getStartDate())) {
            throw new DataFinalInvalidaException();
        }
        if (event.getEndTime().isBefore(event.getStartTime())) {
            throw new HoraFinalInvalidaException();
        }
    }

}
