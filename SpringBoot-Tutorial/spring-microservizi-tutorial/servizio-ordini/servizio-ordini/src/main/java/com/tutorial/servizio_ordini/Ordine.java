package com.tutorial.servizio_ordini;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "ordini")
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "L'id utente non può essere vuoto")
    private Long utenteId;

    @NotBlank(message = "Il prodotto non può essere vuoto")
    private String prodotto;

    @Positive(message = "La quantità deve essere positiva")
    private int quantita;

    // campo calcolato — non viene salvato in DB
    // serve solo per mostrare i dati dell'utente nella risposta
    @Transient
    private UtenteResponse utente;

    public Ordine() {}

    public Ordine(Long utenteId, String prodotto, int quantita) {
        this.utenteId = utenteId;
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    public Long getId() { return id; }
    public Long getUtenteId() { return utenteId; }
    public String getProdotto() { return prodotto; }
    public int getQuantita() { return quantita; }
    public UtenteResponse getUtente() { return utente; }
    public void setUtenteId(Long utenteId) { this.utenteId = utenteId; }
    public void setProdotto(String prodotto) { this.prodotto = prodotto; }
    public void setQuantita(int quantita) { this.quantita = quantita; }
    public void setUtente(UtenteResponse utente) { this.utente = utente; }
}