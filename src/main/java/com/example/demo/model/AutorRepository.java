package com.example.demo.model;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface AutorRepository extends CrudRepository<Autor, Long> {

    List<Autor> findByCedula(String cedula);

}
