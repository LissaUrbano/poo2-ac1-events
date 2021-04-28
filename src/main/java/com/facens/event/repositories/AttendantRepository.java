package com.facens.event.repositories;

import com.facens.event.entities.Event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendantRepository extends JpaRepository<Event, Long> {
    
    @Query("SELECT e FROM TB_ADMIN e " 
    + "WHERE " 
    + "LOWER(e.name)        LIKE   LOWER(CONCAT('%', :name, '%'))        AND " 
    + "LOWER(e.email)       LIKE   LOWER(CONCAT('%', :email, '%'))"
    )
    public Page<Event> findAttendeesPageble(Pageable pageRequest, String name, String email);

}
