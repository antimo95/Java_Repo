package com.example.libreria;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoreService {
    private final AutoreRepository autorerepository;

    public AutoreService(AutoreRepository autorerepository){
        this.autorerepository = autorerepository;
    }

    public List<Autore> getAutori(){
        return autorerepository.findAll();
    }

    public Autore getAutoreById(Long id) {
        return autorerepository.findById(id)
                .orElseThrow(() -> new AutoreNotFoundException(id));
    }

    public Autore aggiungiAutore(Autore autore){
        return autorerepository.save(autore);
    }

    public Autore aggiornaAutore(long id, Autore autoreAggiornato){
        Autore autore = autorerepository.findById(id)
                .orElseThrow(() -> new AutoreNotFoundException(id));
        autore.setNome(autoreAggiornato.getNome());
        autore.setCognome(autoreAggiornato.getCognome());
        return autorerepository.save(autore);
    }

    public void eliminaAutore(Long id){
        if(!autorerepository.existsById(id)){
            throw new AutoreNotFoundException(id);
        }

        autorerepository.deleteById(id);
    }
}
