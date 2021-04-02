package com.facens.event.services;

import com.facens.event.dto.EventDTO;
import com.facens.event.entities.Event;
import com.facens.event.repositories.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public EventDTO insert(EventDTO eventDTO) {
        Event event = new Event(eventDTO);
        return new EventDTO(eventRepository.save(event));
    }
    
}
