package com.tutorial.servizio_utenti;

public class UtenteNotFoundException extends RuntimeException {
    public UtenteNotFoundException(Long id) {

        super("Utente con id " + id + " non trovato");
    }
}
