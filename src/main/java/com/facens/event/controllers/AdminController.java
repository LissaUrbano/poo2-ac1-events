package com.facens.event.controllers;

import java.net.URI;

import javax.validation.Valid;

import com.facens.event.dto.AdminDTO;
import com.facens.event.services.AdminService;

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
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

	/*
	@GetMapping
    public ResponseEntity<Page<AdminDTO>> getAdmins(
		@RequestParam(value = "page", 			defaultValue = "0") Integer page,
		@RequestParam(value = "linesPerPage", 	defaultValue = "6") Integer linesPerPage,
		@RequestParam(value = "direction", 		defaultValue = "ASC") String direction,
		@RequestParam(value = "orderBy", 		defaultValue = "id") String orderBy
	){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
        Page<AdminDTO> list = adminService.getAdmins(pageRequest);
        return ResponseEntity.ok().body(list);
    }
	*/

    @PostMapping
	public ResponseEntity<AdminDTO> insert(@Valid @RequestBody AdminDTO adminDTO){
		AdminDTO dto = adminService.insert(adminDTO); 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

    @DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		adminService.delete(id); 
		return ResponseEntity.noContent().build();
	}

    @GetMapping("{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id){
        AdminDTO dto = adminService.getAdminById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("{id}")
	public ResponseEntity<AdminDTO> update(@Valid @RequestBody AdminDTO updateDto, @PathVariable Long id){
		AdminDTO dto = adminService.update(id, updateDto); 
		return ResponseEntity.ok().body(dto);
	}
    
}
