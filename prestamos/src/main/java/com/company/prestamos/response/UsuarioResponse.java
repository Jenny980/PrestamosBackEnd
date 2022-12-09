package com.company.prestamos.response;

import java.util.List;

import com.company.prestamos.model.Usuario;

import lombok.Data;

@Data
public class UsuarioResponse {
	
	private List<Usuario> usuario;

}
