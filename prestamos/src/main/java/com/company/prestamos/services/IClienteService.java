package com.company.prestamos.services;

import org.springframework.http.ResponseEntity;

import com.company.prestamos.model.Cliente;
import com.company.prestamos.response.ClienteResponseRest;
import com.company.prestamos.response.PrestamoResponseRest;

public interface IClienteService {

	public ResponseEntity<ClienteResponseRest> search();
	public ResponseEntity<ClienteResponseRest> searchById(Long id);
	public ResponseEntity<ClienteResponseRest> save(Cliente cliente);
	public ResponseEntity<ClienteResponseRest> update(Cliente cliente, Long id);
	public ResponseEntity<ClienteResponseRest> deleteById(Long id);
	public ResponseEntity<ClienteResponseRest> searchByNombre(String nombre);
}
