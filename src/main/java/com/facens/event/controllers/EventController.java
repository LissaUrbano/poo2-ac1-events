package com.facens.event.controllers;

import java.net.URI;

import com.facens.event.dto.EventDTO;
import com.facens.event.services.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
	public ResponseEntity<EventDTO> insert(@RequestBody EventDTO eventDTO){
		EventDTO dto = eventService.insert(eventDTO); 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

    @DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		eventService.delete(id); 
		return ResponseEntity.noContent().build();
	}

    @GetMapping("{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id){
        EventDTO dto = eventService.getEventById(id);
        return ResponseEntity.ok().body(dto);
    }
    
}
