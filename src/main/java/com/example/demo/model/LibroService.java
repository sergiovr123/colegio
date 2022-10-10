package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LibroService {
    @Autowired LibroRepository repository;

    public void add(Libro dto) {
        repository.save(toEntity(dto));
    }

    public void modify(Libro dto) {
        repository.save(dto);
    }
    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<Libro> getLibros() {
        return (List<Libro>) repository.findAll();
    }

    public Libro getLibroById(long id) {
        Optional<Libro> optionalLibro = repository.findById(id);
        return optionalLibro.orElseThrow();
    }
    
 
    private Libro toEntity(Libro dto) {
    	Libro entity = new Libro();
        entity.setISBN(dto.getISBN());
        entity.setEditorial(dto.getEditorial());
        entity.setGenero(dto.getGenero());
        entity.setAno_publicacion(dto.getAno_publicacion());
        entity.setAutor(dto.getAutor());
        return entity;
    }
}