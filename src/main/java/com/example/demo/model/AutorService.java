package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AutorService {
    @Autowired AutorRepository repository;

    public void add(Autor dto) {
        repository.save(toEntity(dto));
    }

    public void modify(Autor dto) {
        repository.save(dto);
    }
    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<Autor> getAutors() {
        return (List<Autor>) repository.findAll();
    }

    public Autor getAutorById(long id) {
        Optional<Autor> optionalAutor = repository.findById(id);
        return optionalAutor.orElseThrow();
    }
    
    public List<Autor>  getAutorBycedula(String cedula) {
    	List<Autor> optionalAutor = repository.findByCedula(cedula);
    	if(optionalAutor!=null && !optionalAutor.isEmpty()) {
    		System.out.println("tiene informacion");
    	}else {
    		System.out.println("NO tiene informacion");
    	}
        return optionalAutor;
    }
    
 
    private Autor toEntity(Autor dto) {
    	Autor entity = new Autor();
        entity.setCedula(dto.getCedula());
        entity.setNombre_completo(dto.getNombre_completo());
        entity.setNacionalidad(dto.getNacionalidad());
        return entity;
    }
}