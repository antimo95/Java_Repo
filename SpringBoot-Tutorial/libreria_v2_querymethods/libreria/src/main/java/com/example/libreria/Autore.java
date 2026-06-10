package com.example.libreria;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

import java.util.List;

@Entity
@Table(name = "autori")
public class Autore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Il nome non può essere vuoto")
    private String nome;

    @NotBlank(message = "Il cognome non può essere vuoto")
    private String cognome;

    /* Un autore ha molti libri (1 a molti)
       MappedBy = "autore" dice a JPA che "autore" è il proprietario
       nella classe libro
       Con Cascade se salvo/elimino un autore allora si proapga sui libri
    */

    @OneToMany(mappedBy = "autore", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Libro> libri;

    // Costruttore vuoto per JPA
    public Autore() {}

    public Autore(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    //Getter
    public Long getId(){ return id; }
    public String getNome(){ return nome; }
    public String getCognome(){ return cognome; }
    public List<Libro> getLibri(){ return libri; }

    //Setter
    public void setNome(String nome ){ this.nome = nome; }
    public void setCognome(String cognome){ this.cognome = cognome; }
    public void setLibri(List<Libro> libri){ this.libri = libri; }

}
