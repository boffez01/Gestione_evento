package it.epicode.Gestione_evento.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtenteDTO {

    private String username;
    private String email;
    private String password;
}
