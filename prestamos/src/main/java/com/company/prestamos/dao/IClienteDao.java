package com.company.prestamos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.company.prestamos.model.Cliente;
import com.company.prestamos.model.Prestamo;

public interface IClienteDao extends CrudRepository<Cliente, Long>{
	
	List<Cliente> findBynombreContainingIgnoreCase(String name);

}
