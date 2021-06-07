package com.facens.event.services;

import java.util.List;
import java.util.Optional;

import com.facens.event.dto.AdminDTO;
import com.facens.event.entities.Admin;
import com.facens.event.entities.Event;
import com.facens.event.repositories.AdminRepository;
import com.facens.event.repositories.EventRepository;

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

    @Autowired
    private EventRepository eventRepository;

    private String msgNotFound = "Admin not found";

    public Page<AdminDTO> getAdmins(PageRequest pageRequest) {
        Page<Admin> list = adminRepository.findAdminsPageble(pageRequest);
        return list.map( e -> new AdminDTO(e));
    }

    public AdminDTO insert(AdminDTO adminDTO) {
        validateEmailAdmin(adminDTO);
        return new AdminDTO(adminRepository.save(new Admin(adminDTO)));
    }

    public void delete(Long id) {
        try {
            List<Event> events = eventRepository.findAll();
            for (Event event : events) {
                if (event.getAdmin().getId().equals(id)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possivel excluir, Admin possui eventos cadastrados");
                }
            }
            adminRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound);
        }
    }

    public AdminDTO getAdminDtoById(Long id) {
        return new AdminDTO(getAdminById(id));
    }

    public AdminDTO update(Long id, AdminDTO adminDTO) {
        Admin admin = getAdminById(id);

        //não seta valores Null que vieram do DTO
        if (adminDTO.getName() != null) {
            admin.setName(adminDTO.getName());
        }
        if (adminDTO.getPhoneNumber() != null) {
            admin.setPhoneNumber(adminDTO.getPhoneNumber());
        }
        if (adminDTO.getEmail() != null) {
            validateEmailAdmin(adminDTO);
            admin.setEmail(adminDTO.getEmail());
        }

        return new AdminDTO(adminRepository.save(admin));
    }

    // Validação do Email - Admin
    public void validateEmailAdmin(AdminDTO adminDTO){
        Optional<Admin> admin = adminRepository.findAdminByEmail(adminDTO.getEmail());
        if(admin.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já possui admin com esse email");
        }
    }

    public Admin getAdminById(Long id) {
        Optional<Admin> op = adminRepository.findById(id);
        return op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound));
    }
}
