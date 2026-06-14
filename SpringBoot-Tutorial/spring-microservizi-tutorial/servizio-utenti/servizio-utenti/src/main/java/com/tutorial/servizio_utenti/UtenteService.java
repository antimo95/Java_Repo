package com.tutorial.servizio_utenti;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;

    public UtenteService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    public List<Utente> getUtenti(){
        return utenteRepository.findAll();
    }

    public Utente getUtenteById(Long id){
        return utenteRepository.findById(id)
                .orElseThrow(() -> new UtenteNotFoundException(id));
    }

    public Utente aggiungiutente(Utente utente){
        return utenteRepository.save(utente);
    }

    public void eliminaUtente(long id){
        if(!utenteRepository.existsById(id)){
            throw new UtenteNotFoundException(id);
        }
        utenteRepository.deleteById(id);
    }
}
