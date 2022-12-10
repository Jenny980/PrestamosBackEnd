package com.company.prestamos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.prestamos.model.Category;
import com.company.prestamos.model.Cliente;
import com.company.prestamos.response.CategoryResponseRest;
import com.company.prestamos.response.ClienteResponseRest;
import com.company.prestamos.response.PrestamoResponseRest;
import com.company.prestamos.services.ICategoryService;
import com.company.prestamos.services.IClienteService;

	@CrossOrigin(origins = {"http://localhost:4200"})
	@RestController
	@RequestMapping("/api/v2")
	public class ClienteRestController {
	
		@Autowired
		private IClienteService service;
		
		@GetMapping("/clientes")
		public ResponseEntity<ClienteResponseRest> searchClientes(){
			ResponseEntity<ClienteResponseRest> response = service.search();
			return response;
		}
		
		@GetMapping("/clientes/{id}")
		public ResponseEntity<ClienteResponseRest> searchClientesById(@PathVariable Long id){
			ResponseEntity<ClienteResponseRest> response = service.searchById(id);
			return response;
		}
		
		@PostMapping("/clientes")
		public ResponseEntity<ClienteResponseRest> save(@RequestBody Cliente cliente){
			ResponseEntity<ClienteResponseRest> response = service.save(cliente);
			return response;
		}
		
		@PutMapping("/clientes/{id}")
		public ResponseEntity<ClienteResponseRest> update(@RequestBody Cliente cliente, @PathVariable Long id){
			ResponseEntity<ClienteResponseRest> response = service.update(cliente, id);
			return response;
		}
		
		@DeleteMapping("/clientes/{id}")
		public ResponseEntity<ClienteResponseRest> delete(@PathVariable Long id){
			ResponseEntity<ClienteResponseRest> response = service.deleteById(id);
			return response;
		}
		
		@GetMapping("/clientes/filter/{nombre}")
		public ResponseEntity<ClienteResponseRest> searchByName(@PathVariable String nombre){
			ResponseEntity<ClienteResponseRest> response = service.searchByNombre(nombre);
			return response;
		}

}
