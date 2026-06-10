package com.example.libreria;

public class LibroNotFoundException extends RuntimeException {
    public LibroNotFoundException(Long id) {
        super("Libro con id "+ id + "non trovato");
    }
}
