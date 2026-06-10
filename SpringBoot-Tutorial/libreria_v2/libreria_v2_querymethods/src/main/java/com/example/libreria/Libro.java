package com.example.libreria;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "libri")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il titolo non può essere vuoto")
    private String titolo;

    @Min(value = 1000, message = "Anno non valido")
    @Max(value = 2100, message = "Anno non valido")
    private int anno;

    //Molti libri appartengono allo stesso autore
    //@JoinColumn dice a JPA il nome della colonna chiave esterna

    @ManyToOne
    @JoinColumn(name = "autore_id")
    @JsonBackReference
    private Autore autore;

    public Libro() {}

    public Libro(String titolo, int anno, Autore autore) {
        this.titolo = titolo;
        this.anno = anno;
        this.autore = autore;
    }

    public Long getId() { return id; }
    public String getTitolo() { return titolo; }
    public int getAnno() { return anno; }
    public Autore getAutore() { return autore; }
    public void setTitolo(String titolo) { this.titolo = titolo; }
    public void setAnno(int anno) { this.anno = anno; }
    public void setAutore(Autore autore) { this.autore = autore; }
}
