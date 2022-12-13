package com.company.prestamos.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.prestamos.model.Cliente;
import com.company.prestamos.model.Prestamo;
import com.company.prestamos.response.ClienteResponseRest;
import com.company.prestamos.response.PrestamoResponseRest;
import com.company.prestamos.services.IClienteService;
import com.company.prestamos.services.IPrestamoService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v2")
public class PrestamoRestController {
	
	private IPrestamoService service;
	LocalDate localDate = LocalDate.now();
	
	
	public PrestamoRestController(IPrestamoService service) {
		super();
		this.service = service;
	}

	

	@PostMapping("/prestamos")
	public ResponseEntity<PrestamoResponseRest> save(
			@RequestParam("credito") int credito,
			@RequestParam("periodoPago") String periodoPago,
			@RequestParam("Npagos") int Npagos,
			@RequestParam("valorCuota") int valorCuota,
			@RequestParam("debe") int debe,
			@RequestParam("porcentaje") int porcentaje,
			@RequestParam("estado") String estado,
			@RequestParam("clienteId") Long clienteID) throws IOException
	{
		Prestamo prestamo = new Prestamo();
		prestamo.setCredito(credito);
		prestamo.setPeriodoPago(periodoPago);
		prestamo.setNpagos(Npagos);
		prestamo.setValorCuota(valorCuota);
		prestamo.setDebe(debe);
		prestamo.setFecha(localDate);
		prestamo.setPorcentaje(porcentaje);
		prestamo.setEstado(estado);
		ResponseEntity<PrestamoResponseRest> response = service.save(prestamo, clienteID);
		return response;
	}
	
	@GetMapping("/prestamos/{id}")
	public ResponseEntity<PrestamoResponseRest> searchById(@PathVariable Long id){
		ResponseEntity<PrestamoResponseRest> response = service.searchById(id);
		return response;
	}
	
	@GetMapping("/prestamos")
	public ResponseEntity<PrestamoResponseRest> searchPrestamos(){
		ResponseEntity<PrestamoResponseRest> response = service.search();
		return response;
	}
	
	@GetMapping("/prestamos/filter/{periodoPago}")
	public ResponseEntity<PrestamoResponseRest> searchByPeriodoPago(@PathVariable String periodoPago){
		ResponseEntity<PrestamoResponseRest> response = service.searchByPeriodoPago(periodoPago);
		return response;
	}
	
	@GetMapping("/prestamos/client/{clienteId}")
	public ResponseEntity<PrestamoResponseRest> searchByClienteId(@PathVariable Long clienteId){
		ResponseEntity<PrestamoResponseRest> response = service.searchByClienteId(clienteId);
		return response;
	}
	
	@PutMapping("/prestamos/{id}")
	public ResponseEntity<PrestamoResponseRest> update(
			@RequestParam("credito") int credito,
			@RequestParam("periodoPago") String periodoPago,
			@RequestParam("Npagos") int Npagos,
			@RequestParam("valorCuota") int valorCuota,
			@RequestParam("debe") int debe,
			@RequestParam("porcentaje") int porcentaje,
			@RequestParam("estado") String estado,
			@RequestParam("clienteId") Long clienteID,
			@PathVariable Long id) throws IOException
	{
		Prestamo prestamo = new Prestamo();
		prestamo.setCredito(credito);
		prestamo.setPeriodoPago(periodoPago);
		prestamo.setNpagos(Npagos);
		prestamo.setValorCuota(valorCuota);
		prestamo.setDebe(debe);
		prestamo.setPorcentaje(porcentaje);
		prestamo.setEstado(estado);
		//prestamo.setFecha(localDate);
		ResponseEntity<PrestamoResponseRest> response = service.update(prestamo, clienteID, id);
		return response;
	}
	
	@DeleteMapping("/prestamos/{id}")
	public ResponseEntity<PrestamoResponseRest> deleteById(@PathVariable Long id){
		ResponseEntity<PrestamoResponseRest> response = service.deleteById(id);
		return response;
	}

}
