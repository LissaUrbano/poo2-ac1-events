package com.facens.event.repositories;

import com.facens.event.entities.Attend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendRepository extends JpaRepository<Attend, Long> {
    
    @Query("SELECT e FROM TB_ADMIN e " 
    + "WHERE " 
    + "LOWER(e.name)        LIKE   LOWER(CONCAT('%', :name, '%'))        AND " 
    + "LOWER(e.email)       LIKE   LOWER(CONCAT('%', :email, '%'))"
    )
    public Page<Attend> findAttendeesPageble(Pageable pageRequest, String name, String email);

}
