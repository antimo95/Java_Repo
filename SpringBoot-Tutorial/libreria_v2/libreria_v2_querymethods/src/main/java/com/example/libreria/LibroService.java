package com.example.libreria;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutoreRepository autoreRepository;

    public LibroService(LibroRepository libroRepository,
                        AutoreRepository autoreRepository){

        this.libroRepository = libroRepository;
        this.autoreRepository = autoreRepository;
    }

    public List<Libro> getLibri(){
        return libroRepository.findAll();
    }

    public Libro getById(long id){
        return libroRepository.findById(id)
                .orElseThrow(() -> new LibroNotFoundException(id));
    }

    // Per Aggiungere un libro server l'id dell'autore
    public Libro aggiungiLibro(long autoreId, Libro libro){
        Autore autore = autoreRepository.findById(autoreId)
                .orElseThrow(() -> new AutoreNotFoundException(autoreId));
        libro.setAutore(autore);
        return libroRepository.save(libro);
    }

    public void eliminaLibro(long id){
        if (!libroRepository.existsById(id)) {
            throw new LibroNotFoundException(id);
        }
        libroRepository.deleteById(id);
    }


    //------------- NUOVI METODI ----------------
    public List<Libro> cercaPerTitolo(String parola){
        return libroRepository.findByTitoloContainingIgnoreCase(parola);
    }

    public List<Libro> cercaPerAnno(int anno){
        return libroRepository.findByAnnoGreaterThan(anno);
    }

    public List<Libro> cercaPeriodo(int annoInizio, int annoFine){
        return libroRepository.findByAnnoBetween(annoInizio, annoFine);
    }

    public List<Libro> cercaPerAutore(Long autoreId){
        return libroRepository.findByAutoreId(autoreId);
    }

    public List<Libro> cercaPerCognomeAutore(String cognome) {
        return libroRepository.findLibriByAutoreCognome(cognome);
    }

    public List<Libro> cercaPerAnnoEParola(int anno, String parola) {
        return libroRepository.findByAnnoEParola(anno, parola);

    }
}
