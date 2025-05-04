package it.epicode.Gestione_evento.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrenotazioneDTO {

    private Long utenteId;
    private Long eventoId;
}
