package it.epicode.Gestione_evento.controller;

import it.epicode.Gestione_evento.dto.EventoDTO;
import it.epicode.Gestione_evento.dto.EventoResponseDTO;
import it.epicode.Gestione_evento.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/crea")
    public EventoResponseDTO creaEvento(@RequestBody EventoDTO eventoDTO) {
        return eventoService.creaEvento(eventoDTO);
    }

    @GetMapping("/tutti")
    public List<EventoResponseDTO> getAllEventi() {
        return eventoService.getAllEventi();
    }

    @GetMapping("/{id}")
    public EventoResponseDTO getEventoById(@PathVariable Long id) {
        return eventoService.getEventoById(id);
    }

    @PutMapping("/{id}")
    public EventoResponseDTO modificaEvento(@PathVariable Long id, @RequestBody EventoDTO eventoDTO) {
        return eventoService.modificaEvento(id, eventoDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminaEvento(@PathVariable Long id) {
        eventoService.eliminaEvento(id);
    }
}
