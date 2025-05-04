package it.epicode.Gestione_evento.controller;

import it.epicode.Gestione_evento.dto.UtenteDTO;
import it.epicode.Gestione_evento.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/login")
    public String login(@RequestBody UtenteDTO utenteDTO) {
        return utenteService.login(utenteDTO);
    }

    @PostMapping("/register")
    public String register(@RequestBody UtenteDTO utenteDTO) {
        return utenteService.register(utenteDTO);
    }
}
