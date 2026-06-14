package com.tutorial.servizio_ordini;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrdineService {

    private final OrdineRepository ordineRepository;
    private final UtenteClient utenteClient; // ← iniettato da Spring/Feign

    public OrdineService(OrdineRepository ordineRepository,
                         UtenteClient utenteClient) {
        this.ordineRepository = ordineRepository;
        this.utenteClient = utenteClient;
    }

    public List<Ordine> getOrdini() {
        List<Ordine> ordini = ordineRepository.findAll();

        // per ogni ordine va a prendere i dati dell'utente
        ordini.forEach(o -> {
            UtenteResponse utente = utenteClient.getUtenteById(o.getUtenteId());
            o.setUtente(utente);
        });
        return ordini;
    }

    public Ordine getOrdineById(Long id) {
        Ordine ordine = ordineRepository.findById(id)
                .orElseThrow(() -> new OrdineNotFoundException(id));

        // chiama il Servizio Utenti per ottenere i dati dell'utente
        UtenteResponse utente = utenteClient.getUtenteById(ordine.getUtenteId());
        ordine.setUtente(utente);
        return ordine;
    }

    public List<Ordine> getOrdiniByUtente(Long utenteId) {
        // verifica che l'utente esista prima di cercare i suoi ordini
        utenteClient.getUtenteById(utenteId);
        return ordineRepository.findByUtenteId(utenteId);
    }

    public Ordine creaOrdine(Ordine ordine) {
        // verifica che l'utente esista prima di creare l'ordine
        // se non esiste Feign lancia automaticamente un'eccezione
        utenteClient.getUtenteById(ordine.getUtenteId());
        return ordineRepository.save(ordine);
    }

    public void eliminaOrdine(Long id) {
        if (!ordineRepository.existsById(id)) {
            throw new OrdineNotFoundException(id);
        }
        ordineRepository.deleteById(id);
    }
}
