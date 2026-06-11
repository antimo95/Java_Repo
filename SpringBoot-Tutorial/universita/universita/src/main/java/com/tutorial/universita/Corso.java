package com.tutorial.universita;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Entity
public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il nome non può essere vuoto")
    private String nome;

    @Positive(message = "I posti massimi devono essere un numero positivo")
    private int postiMassimi;

    @OneToMany(mappedBy = "corso", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Studente> studenti;

    public Corso() {}

    public Corso(String nome, int postiMassimi) {
        this.nome = nome;
        this.postiMassimi = postiMassimi;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public int getPostiMassimi() { return postiMassimi; }
    public List<Studente> getStudenti() { return studenti; }
    public void setNome(String nome) { this.nome = nome; }
    public void setPostiMassimi(int postiMassimi) { this.postiMassimi = postiMassimi; }
    public void setStudenti(List<Studente> studenti) { this.studenti = studenti; }
}
