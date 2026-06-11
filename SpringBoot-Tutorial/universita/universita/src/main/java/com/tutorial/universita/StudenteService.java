package com.tutorial.universita;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudenteService {

    private final StudenteRepository studenteRepository;
    private final CorsoRepository corsoRepository;

    public StudenteService(StudenteRepository studenteRepository, CorsoRepository corsoRepository) {
        this.studenteRepository = studenteRepository;
        this.corsoRepository = corsoRepository;
    }


    public List<Studente> GetStudenti(){
        return studenteRepository.findAll();
    }

    public Studente GetStudenteById(Long id){
        return studenteRepository.findById(id)
                .orElseThrow(() -> new StudenteNotFoundException(id));
    }

    public Studente aggiungiStudente(Long corsoId, Studente studente){
        Corso corso = corsoRepository.findById(corsoId)
                .orElseThrow(() -> new CorsoNotFoundException(corsoId));
        studente.setCorso(corso);
        return studenteRepository.save(studente);
    }

    public Studente cambiaCorso(Long studenteId, Long nuovoCorsoId){
        Studente studente = studenteRepository.findById(studenteId)
                .orElseThrow(() -> new StudenteNotFoundException(studenteId));
        Corso nuovoCorso = corsoRepository.findById(nuovoCorsoId)
                .orElseThrow(() -> new CorsoNotFoundException(nuovoCorsoId));
        studente.setCorso(nuovoCorso);

        return studenteRepository.save(studente);
    }

    public void eliminaStudente(long id){
        if(!studenteRepository.existsById(id)){
            throw new StudenteNotFoundException(id);
        }
        studenteRepository.deleteById(id);
    }
}
