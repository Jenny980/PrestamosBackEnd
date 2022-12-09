package com.company.prestamos.services;

import org.springframework.http.ResponseEntity;

import com.company.prestamos.model.Cliente;
import com.company.prestamos.model.Usuario;
import com.company.prestamos.response.UsuarioResponseRest;

public interface IUsuarioService {
	
	public ResponseEntity<UsuarioResponseRest> search();
	public ResponseEntity<UsuarioResponseRest> save(Usuario usuario);

}
