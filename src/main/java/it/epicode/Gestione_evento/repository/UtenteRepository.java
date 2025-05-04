package it.epicode.Gestione_evento.repository;

import it.epicode.Gestione_evento.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Optional<Utente> findByUsername(String username);
    Optional<Utente> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
