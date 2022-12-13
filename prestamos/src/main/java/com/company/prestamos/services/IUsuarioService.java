package com.company.prestamos.services;

import org.springframework.http.ResponseEntity;

import com.company.prestamos.model.Usuario;
import com.company.prestamos.response.UsuarioResponseRest;

public interface IUsuarioService {
	
	public ResponseEntity<UsuarioResponseRest> search();
	public ResponseEntity<UsuarioResponseRest> save(Usuario usuario);
	public ResponseEntity<UsuarioResponseRest> searchByEmail(String email);
	public ResponseEntity<UsuarioResponseRest> searchById(Long id);
	public ResponseEntity<UsuarioResponseRest> update(Usuario usuario, Long id);

}
