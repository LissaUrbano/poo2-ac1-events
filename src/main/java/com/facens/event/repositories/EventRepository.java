package com.facens.event.repositories;

import java.time.LocalDate;

import com.facens.event.entities.Event;
import com.facens.event.entities.Place;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e " 
    + "WHERE " 
    + "LOWER(e.name)        LIKE   LOWER(CONCAT('%', :name, '%'))        AND " 
    //+ "LOWER(e.places.name) LIKE   LOWER(CONCAT('%', :namePlace, '%'))    AND "
    + "LOWER(e.description) LIKE   LOWER(CONCAT('%', :description, '%')) AND "
    + "e.startDate >= :date"
    )
    public Page<Event> findEventsPageble(Pageable pageRequest, String name, /*String namePlace,*/ LocalDate date, String description);
}
