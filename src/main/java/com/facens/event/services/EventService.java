package com.facens.event.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.facens.event.dto.EventDTO;
import com.facens.event.dto.TicketAttendDTO;
import com.facens.event.dto.TicketPostDTO;
import com.facens.event.dto.TicketsEventDTO;
import com.facens.event.entities.Admin;
import com.facens.event.entities.Attend;
import com.facens.event.entities.Event;
import com.facens.event.entities.Place;
import com.facens.event.entities.Ticket;
import com.facens.event.entities.TypeTicket;
import com.facens.event.repositories.AdminRepository;
import com.facens.event.repositories.AttendRepository;
import com.facens.event.repositories.EventRepository;
import com.facens.event.repositories.PlaceRepository;
import com.facens.event.repositories.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private AttendRepository attendRepository;

    private String msgNotFound = "Event not found";

    public Page<EventDTO> getEvents(PageRequest pageRequest, String name, /*String namePlace,*/ String startDate, String description) {
        LocalDate date = convertLocalDate(startDate);
        Page<Event> list = eventRepository.findEventsPageble(pageRequest, name, /*namePlace,*/ date, description);
        return list.map( e -> new EventDTO(e));
    }

    public EventDTO insert(EventDTO eventDTO) {
        validarDataHora(eventDTO);
        Admin admin;
        try {
            admin = adminRepository.findById(eventDTO.getAdmin()).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O admin informado inválido");
        }
        Event event = new Event(eventDTO);
        event.setAdmin(admin);
        return new EventDTO(eventRepository.save(event));
    }

    public void delete(Long id) {
        try {
            eventRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound);
        }
    }

    public EventDTO getEventById(Long id) {
        Optional<Event> op = eventRepository.findById(id);
        Event event = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound));
        return new EventDTO(event);
    }

    public EventDTO update(Long id, EventDTO eventDTO) { 
        try {
            Event event = eventRepository.getOne(id);
            //não seta valores Null que vieram do DTO
            if(eventDTO.getName() != null) {
                event.setName(eventDTO.getName());
            }
            if(eventDTO.getDescription() != null) {
                event.setDescription(eventDTO.getDescription());
            }
            if(eventDTO.getStartDate() != null) {
                eventAvailable(event);
                event.setStartDate(eventDTO.getStartDate()); 
            }
            if(eventDTO.getEndDate() != null) {
                eventAvailable(event);
                event.setEndDate(eventDTO.getEndDate());
            }
            if(eventDTO.getStartTime() != null) {
                eventAvailable(event);
                event.setStartTime(eventDTO.getStartTime());
            }
            if(eventDTO.getEndTime() != null) {
                eventAvailable(event);
                event.setEndTime(eventDTO.getEndTime()); 
            }
            if(eventDTO.getEmailContact() != null) {
                event.setEmailContact(eventDTO.getEmailContact());
            }

            //valida os novos dados inseridos para Data e Hora
            EventDTO eventValidacao = new EventDTO(event);
            validarDataHora(eventValidacao);
            
            event = eventRepository.save(event);
            return new EventDTO(event);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound);
        }
    }
    
    private void validarDataHora(EventDTO eventDTO) {

        if (eventDTO.getStartDate().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data inicial informada deve ser igual ou maior que a data de hoje");
        }
        if (eventDTO.getEndDate().isBefore(eventDTO.getStartDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data final informada deve ser igual ou maior que a data inicial do evento");
        }
        if (eventDTO.getStartDate().isEqual(LocalDate.now()) && eventDTO.getStartTime().isBefore(LocalTime.now()) ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Para eventos na data de hoje a hora inicial deve ser igual ou maior ao horario atual");
        }
        if (eventDTO.getEndTime().isBefore(eventDTO.getStartTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A hora final informada deve ser maior que a hora inicial do evento");
        }
    }

    private LocalDate convertLocalDate(String startDate) {
        if (!startDate.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");    
            try {
                LocalDate date = LocalDate.parse(startDate, formatter);
                return date;
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de filtro inválida, inserir no formato dia/mês/ano. exemplo: 03/04/2021");
            }  
        } else {
            return LocalDate.of(2000, 01, 01) ; //date default caso null
        }     
    }

    
	//****************** ITEM 01 - AF **********************
	public void associatePlaceEvent(Long eventId, Long placeId) {
        Optional<Event> opEvent = eventRepository.findById(eventId);
        Event event = opEvent.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound));

        eventAvailable(event);

        Optional<Place> opPlace = placeRepository.findById(placeId);
        Place place = opPlace.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found"));

        if (!existPlaceInEvent(event, place)) {
            placeAvailable(event, place);
            event.addPlaces(place);
            place.addEvents(event);
            eventRepository.save(event);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Local já cadastrado no evento");
        }
	}

    public void removePlaceEvent(Long eventId, Long placeId) {
        Optional<Event> opEvent = eventRepository.findById(eventId);
        Event event = opEvent.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound));

        eventAvailable(event);

        Optional<Place> opPlace = placeRepository.findById(placeId);
        Place place = opPlace.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found"));

        if (existPlaceInEvent(event, place)) {
            event.removePlaces(place);
            place.removeEvent(event);
            eventRepository.save(event);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Local não cadastrado no evento");
        }
    }

    private void eventAvailable(Event event){
        if (event.getStartDate().isBefore(LocalDate.now()) || event.getStartDate().isEqual(LocalDate.now()) && event.getStartTime().isAfter(LocalTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O evento já foi iniciado e não pode ser modificado");
        }
    }

    private boolean existPlaceInEvent(Event event, Place place){
        List<Place> placesEvent = event.getPlaces();
        
        for (Place placeCurrent : placesEvent) {
            if (placeCurrent.equals(place)) {
                return true;
            } 
        }
        return false;
    }

    private void placeAvailable(Event event, Place place) {
        LocalDate startDateEvent = event.getStartDate();
        LocalDate endDateEvent = event.getEndDate();

        List<Event> eventsPlace = place.getEvents();

        //permite um evento por dia
        for (Event eventplace : eventsPlace) {
            if (startDateEvent.isBefore(eventplace.getStartDate()) && endDateEvent.isBefore(eventplace.getStartDate()) ||
            startDateEvent.isAfter(eventplace.getEndDate()) && endDateEvent.isAfter(eventplace.getEndDate()) ) {
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Local não disponivel para a data do evento");
            }
        }
    }

    //****************** ITEM 02 - AF **********************
	//Devolve a lista de ingressos de um evento, tendo o tipo do ingresso e nome dos participantes.
	//Devolve o total de ingressos pagos, total de ingressos gratuitos, total de ingressos pagos já vendidos, total de ingressos gratuitos já vendidos.
    public TicketsEventDTO getTickets(Long id){
        Optional<Event> opEvent = eventRepository.findById(id);
        Event event = opEvent.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, msgNotFound));

        TicketsEventDTO ticketsEventDTO = new TicketsEventDTO(event);

        List<Ticket> ticketsEvent = event.getTickets(); 
        List<Attend> attenddes = attendRepository.findAll();
        
        for (Ticket ticket : ticketsEvent) {
            for (Attend attend : attenddes) {
                for (Ticket ticketAttend : attend.getTickets()) {
                    if (ticketAttend.equals(ticket)) {
                        ticketsEventDTO.addTicketsAttend(new TicketAttendDTO(ticket.getId(), ticket.getType(), attend.getName()));
                    }
                }
            }
        }
        return ticketsEventDTO;
    }


    //****************** ITEM 03 - AF **********************
	//Vende um ingresso para um evento.
	//Passar o id do participante no corpo da requisição.
	//Passar se o ingresso é pago ou gratuito no corpo da requisição.
	//Validar se é possível fazer a venda.
    public void sellTicket(TicketPostDTO ticketDto, Long eventId) {
        Event event;
        try {
            event = eventRepository.findById(eventId).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O evento informado não existe");
        }

        eventAvailable(event);
        validateTicket(ticketDto.getType());
        Ticket ticket = new Ticket(ticketDto);

        if (ticket.getType().equals(TypeTicket.FREE)) {
            if (event.getFreeTickectsSelled() < event.getAmountFreeTickets()) {
                event.addTickets(ticket);
                event.setFreeTickectsSelled(event.getFreeTickectsSelled()+1);
                ticket.setPrice(0.00);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os ingressos gratuitos esgotaram para este evento");
        } else if (ticket.getType().equals(TypeTicket.PAYED)){
            if (event.getPayedTickectsSelled() < event.getAmountPayedTickets()) {
                event.addTickets(ticket);
                event.setPayedTickectsSelled(event.getPayedTickectsSelled()+1);
                ticket.setPrice(event.getPriceTicket());
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os ingressos pagos esgotaram para este evento");
        }
        
        Optional<Attend> opAttend = attendRepository.findById(ticketDto.getAttend());
        Attend attend = opAttend.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend not found"));
        attend.addTickets(ticket);
      
        eventRepository.save(event);
        attendRepository.save(attend);
    }

    private void validateTicket(TypeTicket type) {
        if (!type.equals(TypeTicket.FREE) && !type.equals(TypeTicket.PAYED)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de ingresso inválido");
        } 
    }

    //Na devolução de um ingresso pago, criar saldo para o participante.
    public void returnTicket(Long eventId, Long ticketId) { 
        Event event;
        try {
            event = eventRepository.findById(eventId).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O evento informado não existe");
        }

        eventAvailable(event);

        Optional<Ticket> opTicket = ticketRepository.findById(ticketId);
        Ticket ticketSelled = opTicket.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found"));

        List<Attend> attendList = attendRepository.findAll();
        Attend attend = null;

        for (Attend attendCurrent : attendList) {
            for (Ticket ticket : attendCurrent.getTickets()) {
                if (ticket.equals(ticketSelled)) {
                    attend = attendCurrent;
                    attend.setBalance(attend.getBalance() + ticket.getPrice());
                    attend.removeTicket(ticket);
                }
            }
        }

        attendRepository.save(attend);

        if (ticketSelled.getType() == TypeTicket.FREE) {
            event.setAmountFreeTickets(event.getAmountFreeTickets() + 1);
        } else {
            event.setAmountPayedTickets(event.getAmountPayedTickets() + 1);
        }

        eventRepository.save(event);
    }

}
