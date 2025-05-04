package it.epicode.Gestione_evento.service;

import it.epicode.Gestione_evento.dto.UtenteDTO;
import it.epicode.Gestione_evento.entity.Utente;
import it.epicode.Gestione_evento.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String login(UtenteDTO utenteDTO) {
        Utente utente = utenteRepository.findByUsername(utenteDTO.getUsername()).orElseThrow(() -> new RuntimeException("Utente non trovato"));
        if (!passwordEncoder.matches(utenteDTO.getPassword(), utente.getPassword())) {
            throw new RuntimeException("Credenziali non valide");
        }
        return "Login effettuato con successo!";
    }

    public String register(UtenteDTO utenteDTO) {
        if (utenteRepository.existsByUsername(utenteDTO.getUsername()) || utenteRepository.existsByEmail(utenteDTO.getEmail())) {
            throw new RuntimeException("Username o email già registrati");
        }
        Utente utente = new Utente();
        utente.setUsername(utenteDTO.getUsername());
        utente.setEmail(utenteDTO.getEmail());
        utente.setPassword(passwordEncoder.encode(utenteDTO.getPassword()));
        utenteRepository.save(utente);
        return "Registrazione effettuata con successo!";
    }

    public Utente getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return utenteRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Utente non trovato"));
    }
}
