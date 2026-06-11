package com.tutorial.universita;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorsoService {

    private final CorsoRepository repo;

    public CorsoService(CorsoRepository repo){
        this.repo = repo;
    }

    public List<Corso> getCorsi(){
        return repo.findAll();
    }

    public Corso getCorsoId(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new CorsoNotFoundException(id));
    }

    public Corso aggiungiCorso(Corso corso){
        return repo.save(corso);
    }

    public void eliminaCorso(Long id){
        if(!repo.existsById(id)){
            throw new CorsoNotFoundException(id);
        }
        repo.deleteById(id);
    }

}
