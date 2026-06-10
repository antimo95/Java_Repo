package com.example.libreria;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutoreRepository extends JpaRepository<Autore, Long> {

    //SELECT * FROM autore WHERE Cognome = ?
    Optional<Autore> findByCognome(String cognome);

    //SELECT * FROM autore WHERE nome LIKE %?%
    List<Autore> findByNomeContainingIgnoreCase(String cognome);

    //SELECT * FROM autore WHERE nome = ? AND cognome = ?
    Optional<Autore> findByNomeAndCognome(String nome, String cognome);

}
