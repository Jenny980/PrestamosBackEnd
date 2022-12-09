package com.company.prestamos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Cliente")
public class Cliente implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -2290469013349069515L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nombre;
	private String apellido;
	@Column(unique = true)
	private long cc;
	private long telefono;
	private String direccion;
	private String barrio;

}
