package com.tutorial.servizio_ordini;

public class OrdineNotFoundException extends RuntimeException {
    public OrdineNotFoundException(Long id) {
        super("Ordine con id " + id + " non trovato");
    }
}
