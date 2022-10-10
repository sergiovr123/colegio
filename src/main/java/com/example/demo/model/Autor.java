package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "autor" )
public class Autor {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
    private String cedula;
    private String nombre_completo;
    private String nacionalidad;
    
    public Autor() {
    }
    
    public Autor(String cedula, String nombre_completo, String nacionalidad) {
        this.cedula = cedula;
        this.nombre_completo = nombre_completo;
        this.nacionalidad = nacionalidad;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre_completo() {
		return nombre_completo;
	}

	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

}
