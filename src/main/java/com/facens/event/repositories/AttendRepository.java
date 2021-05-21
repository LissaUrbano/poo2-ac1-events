package com.facens.event.repositories;

import com.facens.event.entities.Attend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendRepository extends JpaRepository<Attend, Long> {
    
    @Query("SELECT a FROM Attend a")
    public Page<Attend> findAttendeesPageble(Pageable pageRequest);

}
