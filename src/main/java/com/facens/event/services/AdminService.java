package com.facens.event.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.facens.event.dto.AdminDTO;
import com.facens.event.entities.Admin;
import com.facens.event.repositories.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    private String msgNotFound = "Admin not found";

    public Page<AdminDTO> getAdmins(PageRequest pageRequest) {
        Page<Admin> list = adminRepository.findAdminsPageble(pageRequest);
        return list.map( e -> new AdminDTO(e));
    }

    public AdminDTO insert(AdminDTO adminDTO) {
        Admin admin = new Admin(adminDTO);
        return new AdminDTO(adminRepository.save(admin));
    }

    public void delete(Long id) {
        try {
            adminRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound);
        }
    }

    public AdminDTO getAdminById(Long id) {
        Optional<Admin> op = adminRepository.findById(id);
        Admin admin = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound));
        return new AdminDTO(admin);
    }

    public AdminDTO update(Long id, AdminDTO adminDTO) { 
        try {
            Admin admin = adminRepository.getOne(id);
            //não seta valores Null que vieram do DTO
            if(adminDTO.getName() != null) {
                admin.setName(adminDTO.getName());
            }
            if(adminDTO.getPhoneNumber() != null) {
                admin.setPhoneNumber(adminDTO.getPhoneNumber()); 
            }
            if(adminDTO.getEmail() != null) {
                admin.setEmail(adminDTO.getEmail());
            }
            
            admin = adminRepository.save(admin);
            return new AdminDTO(admin);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound);
        }
    }
}
