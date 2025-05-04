package it.epicode.Gestione_evento.service;

import it.epicode.Gestione_evento.dto.PrenotazioneDTO;
import it.epicode.Gestione_evento.dto.PrenotazioneResponseDTO;
import it.epicode.Gestione_evento.entity.Evento;
import it.epicode.Gestione_evento.entity.Prenotazione;
import it.epicode.Gestione_evento.entity.Utente;
import it.epicode.Gestione_evento.repository.EventoRepository;
import it.epicode.Gestione_evento.repository.PrenotazioneRepository;
import it.epicode.Gestione_evento.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    public String prenotaEvento(PrenotazioneDTO prenotazioneDTO) {
        Utente utente = utenteRepository.findById(prenotazioneDTO.getUtenteId()).orElseThrow(() -> new RuntimeException("Utente non trovato"));
        Evento evento = eventoRepository.findById(prenotazioneDTO.getEventoId()).orElseThrow(() -> new RuntimeException("Evento non trovato"));

        if (evento.getPostiDisponibili() <= 0) {
            return "Posti esauriti per l'evento.";
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUtente(utente);
        prenotazione.setEvento(evento);
        prenotazioneRepository.save(prenotazione);

        evento.setPostiDisponibili(evento.getPostiDisponibili() - 1);
        eventoRepository.save(evento);

        return "Prenotazione effettuata con successo!";
    }

    public String cancellaPrenotazione(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));
        Evento evento = prenotazione.getEvento();
        evento.setPostiDisponibili(evento.getPostiDisponibili() + 1);
        eventoRepository.save(evento);
        prenotazioneRepository.delete(prenotazione);
        return "Prenotazione cancellata con successo!";
    }

    public List<PrenotazioneDTO> getPrenotazioniByUtente(Long utenteId) {
        List<Prenotazione> prenotazioni = prenotazioneRepository.findByUtenteId(utenteId);
        return prenotazioni.stream()
                .map(prenotazione -> new PrenotazioneDTO(prenotazione.getUtente().getId(), prenotazione.getEvento().getId()))
                .collect(Collectors.toList());
    }
}
