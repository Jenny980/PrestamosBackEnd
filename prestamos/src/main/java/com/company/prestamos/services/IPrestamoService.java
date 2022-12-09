package com.company.prestamos.services;

import org.springframework.http.ResponseEntity;

import com.company.prestamos.model.Prestamo;
import com.company.prestamos.response.ClienteResponseRest;
import com.company.prestamos.response.PrestamoResponseRest;

public interface IPrestamoService {
	
	public ResponseEntity<PrestamoResponseRest> save(Prestamo prestamo, Long clienteId);
	public ResponseEntity<PrestamoResponseRest> searchById(Long id);
	public ResponseEntity<PrestamoResponseRest> search();
	public ResponseEntity<PrestamoResponseRest> searchByPeriodoPago(String periodoPago);
	public ResponseEntity<PrestamoResponseRest> searchByClienteId(Long id);
	public ResponseEntity<PrestamoResponseRest> update(Prestamo prestamo, Long clienteId, Long id);
	public ResponseEntity<PrestamoResponseRest> deleteById(Long id);
}
