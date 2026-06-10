package com.example.libreria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


//Struttura query custom => FindBy + NomeCampo + Condizione
public interface LibroRepository extends JpaRepository<Libro, Long> {

    //SELECT * FROM libri WHERE titolo = ?
    List<Libro> findByTitolo(String titolo);

    //SELECT * FROM libri WHERE anno = ?
    List<Libro> findByAnno(int anno);

    //SELECT * FROM libri WHERE anno > ?
    List<Libro> findByAnnoGreaterThan(int anno);

    //SELECT * FROM libri WHERE anno BETWEEN ? AND ?
    List<Libro> findByAnnoBetween(int annoInizio, int annoFine);

    //SELECT * FROM libri where autore_id = ?
    List<Libro> findByAutoreId(Long autore_id);

    //SELECT * FROM libri WHERE titolo LIKE %?%
    List<Libro> findByTitoloContainingIgnoreCase(String parola);

    // JPQL: nota che usi i nomi delle classi Java (Libro, Autore)
    // non i nomi delle tabelle (libri, autori)
    @Query("SELECT l FROM Libro l WHERE l.autore.cognome = :cognome")
    List<Libro> findLibriByAutoreCognome(@Param("cognome") String cognome);

    // cerca libri per anno e parola nel titolo insieme
    @Query("SELECT l FROM Libro l WHERE l.anno >= :anno AND " +
            "LOWER(l.titolo) LIKE LOWER(CONCAT('%', :parola, '%'))")
    List<Libro> findByAnnoEParola(@Param("anno") int anno,
                                  @Param("parola") String parola);
}
