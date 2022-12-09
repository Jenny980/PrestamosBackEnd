package com.company.prestamos.services;

import org.springframework.http.ResponseEntity;

import com.company.prestamos.model.Cliente;
import com.company.prestamos.response.ClienteResponseRest;

public interface IClienteService {

	public ResponseEntity<ClienteResponseRest> search();
	public ResponseEntity<ClienteResponseRest> searchById(Long id);
	public ResponseEntity<ClienteResponseRest> save(Cliente cliente);
	public ResponseEntity<ClienteResponseRest> update(Cliente cliente, Long id);
	public ResponseEntity<ClienteResponseRest> deleteById(Long id);
}
