package com.tutorial.servizio_utenti;


import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    private final UtenteService utenteservice;

    public UtenteController(UtenteService utenteservice) {
        this.utenteservice = utenteservice;
    }

    @GetMapping
    public List<Utente> getUtenti(){
        return utenteservice.getUtenti();
    }

    @GetMapping("/{id}")
    public Utente getUtenteById(@PathVariable Long id){
        return utenteservice.getUtenteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Utente aggiungiutente(@Valid @RequestBody Utente utente){
        return utenteservice.aggiungiutente(utente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaUtente(@PathVariable long id){
        utenteservice.eliminaUtente(id);
    }
}
