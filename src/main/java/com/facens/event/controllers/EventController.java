package com.facens.event.controllers;

import java.net.URI;

import javax.validation.Valid;

import com.facens.event.dto.EventDTO;
import com.facens.event.dto.PlaceDTO;
import com.facens.event.entities.Place;
import com.facens.event.services.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

	@GetMapping
    public ResponseEntity<Page<EventDTO>> getEvents(
		@RequestParam(value = "page", 			defaultValue = "0") Integer page,
		@RequestParam(value = "linesPerPage", 	defaultValue = "6") Integer linesPerPage,
		@RequestParam(value = "direction", 		defaultValue = "ASC") String direction,
		@RequestParam(value = "orderBy", 		defaultValue = "id") String orderBy,
		@RequestParam(value = "name", 			defaultValue = "") String name,
		@RequestParam(value = "place",          defaultValue = "") Place place,	        
		@RequestParam(value = "startDate",      defaultValue = "") String startDate,
	    @RequestParam(value = "description",    defaultValue = "") String description

	){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
        Page<EventDTO> list = eventService.getEvents(pageRequest, name, place, startDate, description);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
	public ResponseEntity<EventDTO> insert(@Valid @RequestBody EventDTO eventDTO){
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

    @PutMapping("{id}")
	public ResponseEntity<EventDTO> update(@Valid @RequestBody EventDTO updateDto, @PathVariable Long id){
		EventDTO dto = eventService.update(id, updateDto); 
		return ResponseEntity.ok().body(dto);
	}
    
}
