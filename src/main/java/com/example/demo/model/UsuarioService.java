package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsuarioService {
    @Autowired UsuarioRepository repository;

    public void add(Usuario dto) {
        repository.save(toEntity(dto));
    }

    public void modify(Usuario dto) {
        repository.save(dto);
    }
    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<Usuario> getUsuarios() {
        return (List<Usuario>) repository.findAll();
    }

    public Usuario getUsuarioById(long id) {
        Optional<Usuario> optionalUsuario = repository.findById(id);
        return optionalUsuario.orElseThrow();
    }
    
 
    private Usuario toEntity(Usuario dto) {
    	Usuario entity = new Usuario();
        entity.setUser_name(dto.getUser_name());
        entity.setPassword(dto.getPassword());
        entity.setTipo(dto.getTipo());
        return entity;
    }
}