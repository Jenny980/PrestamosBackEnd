package com.company.prestamos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.company.prestamos.model.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
	
	List<Usuario> findByemailContainingIgnoreCase(String email);

}
