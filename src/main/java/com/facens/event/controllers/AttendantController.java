package com.facens.event.controllers;

import java.net.URI;

import com.facens.event.dto.AttendantDTO;
import com.facens.event.services.AttendantService;

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
@RequestMapping("/attendees")
public class AttendantController {

    @Autowired
    private AttendantService attendantService;

	@GetMapping
    public ResponseEntity<Page<AttendantDTO>> getAttendants(
		@RequestParam(value = "page", 			defaultValue = "0") Integer page,
		@RequestParam(value = "linesPerPage", 	defaultValue = "6") Integer linesPerPage,
		@RequestParam(value = "direction", 		defaultValue = "ASC") String direction,
		@RequestParam(value = "orderBy", 		defaultValue = "id") String orderBy,
		@RequestParam(value = "name", 			defaultValue = "") String name,
		@RequestParam(value = "email",          defaultValue = "") String email
	){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
        Page<AttendantDTO> list = attendantService.getAttendants(pageRequest, name, email);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
	public ResponseEntity<AttendantDTO> insert(@RequestBody AttendantDTO attendantDTO){
		AttendantDTO dto = attendantService.insert(attendantDTO); 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

    @DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		attendantService.delete(id); 
		return ResponseEntity.noContent().build();
	}

    @GetMapping("{id}")
    public ResponseEntity<AttendantDTO> getAttendantById(@PathVariable Long id){
        AttendantDTO dto = attendantService.getAttendantById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("{id}")
	public ResponseEntity<AttendantDTO> update(@RequestBody AttendantDTO updateDto, @PathVariable Long id){
		AttendantDTO dto = attendantService.update(id, updateDto); 
		return ResponseEntity.ok().body(dto);
	}
    
}
