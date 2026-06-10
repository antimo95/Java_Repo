package com.example.libreria;

public class AutoreNotFoundException extends RuntimeException {
    public AutoreNotFoundException(Long id) {

        super("Autore con id" + id + "non trovato");
    }
}
