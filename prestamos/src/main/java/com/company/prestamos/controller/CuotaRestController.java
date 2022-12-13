package com.company.prestamos.controller;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.prestamos.model.Cuota;
import com.company.prestamos.model.Prestamo;
import com.company.prestamos.response.CuotaResponseRest;
import com.company.prestamos.response.PrestamoResponseRest;
import com.company.prestamos.services.ICuotaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v2")
public class CuotaRestController {
	
	private ICuotaService service;
	LocalDate localDate = LocalDate.now();
	
	public CuotaRestController(ICuotaService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/cuotas")
	public ResponseEntity<CuotaResponseRest> save(
			@RequestParam("valor") int valor,
			@RequestParam("prestamoId") Long prestamoID) throws IOException
	{
		Cuota cuota = new Cuota();
		cuota.setValor(valor);;
		cuota.setFecha(localDate);

		ResponseEntity<CuotaResponseRest> response = service.save(cuota, prestamoID);
		return response;
	}
	
	@GetMapping("/cuotas/prestamo/{prestamoId}")
	public ResponseEntity<CuotaResponseRest> searchByPrestamoId(@PathVariable Long prestamoId){
		ResponseEntity<CuotaResponseRest> response = service.searchByPrestamoId(prestamoId);
		return response;
	}
	
	

}
