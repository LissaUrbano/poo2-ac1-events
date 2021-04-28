package com.facens.event.repositories;

import com.facens.event.entities.Event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Event, Long> {
    
    @Query("SELECT e FROM TB_ADMIN e " 
    + "WHERE " 
    + "LOWER(e.name)        LIKE   LOWER(CONCAT('%', :name, '%'))        AND " 
    + "LOWER(e.address)       LIKE   LOWER(CONCAT('%', :address, '%'))"
    )
    public Page<Event> findPlacesPageble(Pageable pageRequest, String name, String address);

}
