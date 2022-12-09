package com.company.prestamos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.prestamos.model.Cliente;
import com.company.prestamos.model.Usuario;
import com.company.prestamos.response.ClienteResponseRest;
import com.company.prestamos.response.UsuarioResponseRest;
import com.company.prestamos.services.IUsuarioService;

@RestController
@RequestMapping("/api/v2")
public class UsuarioRestController {
	
	@Autowired
	private IUsuarioService service;
	
	@GetMapping("/usuarios")
	public ResponseEntity<UsuarioResponseRest> searchUsuarios(){
		ResponseEntity<UsuarioResponseRest> response = service.search();
		return response;
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<UsuarioResponseRest> save(@RequestBody Usuario usuario){
		ResponseEntity<UsuarioResponseRest> response = service.save(usuario);
		return response;
	}

}
