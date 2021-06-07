package com.facens.event.services;

import java.util.Optional;

import com.facens.event.entities.Ticket;
import com.facens.event.repositories.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TicketService {
    
    @Autowired
    private TicketRepository ticketRepository;

    private String msgNotFound = "Ticket not found";

    public Ticket getTicketById(Long id) {
        Optional<Ticket> op = ticketRepository.findById(id);
        return op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound));
    }
}
