package it.epicode.Gestione_evento.service;

import it.epicode.Gestione_evento.dto.EventoDTO;
import it.epicode.Gestione_evento.dto.EventoResponseDTO;
import it.epicode.Gestione_evento.entity.Evento;
import it.epicode.Gestione_evento.entity.Utente;
import it.epicode.Gestione_evento.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtenteService utenteService;

    public EventoResponseDTO creaEvento(EventoDTO eventoDTO) {
        Utente organizzatore = utenteService.getLoggedInUser(); // get current logged-in user
        Evento evento = Evento.builder()
                .titolo(eventoDTO.getTitolo())
                .descrizione(eventoDTO.getDescrizione())
                .data(eventoDTO.getData())
                .luogo(eventoDTO.getLuogo())
                .postiDisponibili(eventoDTO.getPostiDisponibili())
                .organizzatore(organizzatore)
                .build();
        eventoRepository.save(evento);
        return new EventoResponseDTO(evento.getId(), evento.getTitolo(), evento.getDescrizione(), evento.getData(), evento.getLuogo(), evento.getPostiDisponibili(), organizzatore.getUsername());
    }

    public List<EventoResponseDTO> getAllEventi() {
        return eventoRepository.findAll().stream()
                .map(evento -> new EventoResponseDTO(evento.getId(), evento.getTitolo(), evento.getDescrizione(), evento.getData(), evento.getLuogo(), evento.getPostiDisponibili(), evento.getOrganizzatore().getUsername()))
                .collect(Collectors.toList());
    }

    public EventoResponseDTO getEventoById(Long id) {
        Evento evento = eventoRepository.findById(id).orElseThrow(() -> new RuntimeException("Evento non trovato"));
        return new EventoResponseDTO(evento.getId(), evento.getTitolo(), evento.getDescrizione(), evento.getData(), evento.getLuogo(), evento.getPostiDisponibili(), evento.getOrganizzatore().getUsername());
    }

    public EventoResponseDTO modificaEvento(Long id, EventoDTO eventoDTO) {
        Evento evento = eventoRepository.findById(id).orElseThrow(() -> new RuntimeException("Evento non trovato"));
        evento.setTitolo(eventoDTO.getTitolo());
        evento.setDescrizione(eventoDTO.getDescrizione());
        evento.setData(eventoDTO.getData());
        evento.setLuogo(eventoDTO.getLuogo());
        evento.setPostiDisponibili(eventoDTO.getPostiDisponibili());
        eventoRepository.save(evento);
        return new EventoResponseDTO(evento.getId(), evento.getTitolo(), evento.getDescrizione(), evento.getData(), evento.getLuogo(), evento.getPostiDisponibili(), evento.getOrganizzatore().getUsername());
    }

    public void eliminaEvento(Long id) {
        eventoRepository.deleteById(id);
    }
}
