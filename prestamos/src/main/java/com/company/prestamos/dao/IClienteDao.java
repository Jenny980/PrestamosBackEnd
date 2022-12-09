package com.company.prestamos.dao;

import org.springframework.data.repository.CrudRepository;
import com.company.prestamos.model.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long>{

}
