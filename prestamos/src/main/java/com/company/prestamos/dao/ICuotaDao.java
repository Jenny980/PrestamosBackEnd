package com.company.prestamos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.company.prestamos.model.Cuota;


public interface ICuotaDao extends CrudRepository<Cuota, Long>{
	
	List<Cuota> findAllByprestamoId(Long id);

}
