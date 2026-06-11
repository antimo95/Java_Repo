package com.tutorial.universita;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Studente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il nome non può essere vuoto")
    private String nome;

    @NotBlank(message = "Il cognome non può essere vuoto")
    private String cognome;

    @NotBlank(message = "L'email non può essere vuota")
    @Email(message = "L'email non è valida")
    private String email;

    @ManyToOne //Molti studenti appartengono ad un singolo corso
    @JoinColumn(name = "corso_id")
    @JsonBackReference
    private Corso corso;

    //Mi serve per JPA
    public Studente() {};

    public Studente(String nome, String cognome, String email, Corso corso){
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.corso = corso;
    }

    //GETTER
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public String getEmail() { return email; }
    public Corso getCorso() { return corso; }

    //SETTER
    public void setNome(String nome) { this.nome = nome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    public void setEmail(String email) { this.email = email; }
    public void setCorso(Corso corso) { this.corso = corso; }

}
