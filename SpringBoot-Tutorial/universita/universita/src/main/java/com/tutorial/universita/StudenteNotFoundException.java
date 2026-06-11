package com.tutorial.universita;

public class StudenteNotFoundException extends RuntimeException {
    public StudenteNotFoundException(Long id) {

        super("Studente con id "+ id + "non trovato");
    }
}
