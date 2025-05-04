package it.epicode.Gestione_evento.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrenotazioneResponseDTO {

    private Long id;
    private Long utenteId;
    private Long eventoId;
}
