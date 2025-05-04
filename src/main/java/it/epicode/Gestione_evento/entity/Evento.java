package it.epicode.Gestione_evento.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private int postiDisponibili;

    @ManyToOne
    @JoinColumn(name = "organizzatore_id")
    private Utente organizzatore;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<Prenotazione> prenotazioni;
}
