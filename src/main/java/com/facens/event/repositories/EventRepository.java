package com.facens.event.repositories;

import com.facens.event.entities.Event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    
    //JPQL
    @Query("SELECT e FROM Event e")
    public Page <Event> findEventsPageble(PageRequest pageRequest);

}
