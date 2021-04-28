package com.facens.event.controllers;

import java.net.URI;

import com.facens.event.dto.PlaceDTO;
import com.facens.event.services.PlaceService;

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
@RequestMapping("/places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

	@GetMapping
    public ResponseEntity<Page<PlaceDTO>> getPlaces(
		@RequestParam(value = "page", 			defaultValue = "0") Integer page,
		@RequestParam(value = "linesPerPage", 	defaultValue = "6") Integer linesPerPage,
		@RequestParam(value = "direction", 		defaultValue = "ASC") String direction,
		@RequestParam(value = "orderBy", 		defaultValue = "id") String orderBy,
		@RequestParam(value = "name", 			defaultValue = "") String name,
		@RequestParam(value = "address",          defaultValue = "") String address
	){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
        Page<PlaceDTO> list = placeService.getPlaces(pageRequest, name, address);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
	public ResponseEntity<PlaceDTO> insert(@RequestBody PlaceDTO placeDTO){
		PlaceDTO dto = placeService.insert(placeDTO); 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

    @DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		placeService.delete(id); 
		return ResponseEntity.noContent().build();
	}

    @GetMapping("{id}")
    public ResponseEntity<PlaceDTO> getPlaceById(@PathVariable Long id){
        PlaceDTO dto = placeService.getPlaceById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("{id}")
	public ResponseEntity<PlaceDTO> update(@RequestBody PlaceDTO updateDto, @PathVariable Long id){
		PlaceDTO dto = placeService.update(id, updateDto); 
		return ResponseEntity.ok().body(dto);
	}
    
}
