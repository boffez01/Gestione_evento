package it.epicode.Gestione_evento.controller;

import it.epicode.Gestione_evento.dto.PrenotazioneDTO;
import it.epicode.Gestione_evento.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping("/prenota")
    public String prenotaEvento(@RequestBody PrenotazioneDTO prenotazioneDTO) {
        return prenotazioneService.prenotaEvento(prenotazioneDTO);
    }

    @DeleteMapping("/{id}")
    public String cancellaPrenotazione(@PathVariable Long id) {
        return prenotazioneService.cancellaPrenotazione(id);
    }

    @GetMapping("/utente/{utenteId}")
    public List<PrenotazioneDTO> getPrenotazioniByUtente(@PathVariable Long utenteId) {
        return prenotazioneService.getPrenotazioniByUtente(utenteId);
    }
}
