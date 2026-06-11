package com.tutorial.universita;

public class CorsoNotFoundException extends RuntimeException {
    public CorsoNotFoundException(Long id) {
        super("Corso con id "+ id + " non trovato");
    }
}
