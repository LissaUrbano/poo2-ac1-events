package com.facens.event.repositories;

import java.util.Optional;

import com.facens.event.entities.Admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    
    @Query("SELECT e FROM Admin e")
    public Page<Admin> findAdminsPageble(Pageable pageRequest);

    
    Optional<Admin> findAdminByEmail(String email);


}
