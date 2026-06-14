package com.tutorial.servizio_ordini;

public class UtenteResponse {

    private Long id;
    private String nome;
    private String cognome;
    private String email;

    public UtenteResponse() {}

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public String getEmail() { return email; }
    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    public void setEmail(String email) { this.email = email; }
}
