package com.company.prestamos.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name="prestamo")
public class Prestamo implements Serializable{
	
	LocalDate localDate = LocalDate.now();
	/**
	 * 
	 */
	private static final long serialVersionUID = 63393709412423859L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long credito;
	private String periodoPago;
	private long Npagos;
	private long valorCuota;
	private long debe;
	private long porcentaje;
	private boolean estado;
	private LocalDate fecha;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Cliente cliente;

}
