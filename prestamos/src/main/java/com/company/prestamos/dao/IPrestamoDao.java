package com.company.prestamos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.company.prestamos.model.Prestamo;

public interface IPrestamoDao extends CrudRepository<Prestamo, Long>{
	
	@Query("select p from Prestamo p where p.periodoPago like %?1%")
	List<Prestamo> findByperiodoPagoLike(String periodoPago);
	
	List<Prestamo> findByperiodoPagoContainingIgnoreCase(String periodoPago);
	
	//List<Prestamo> findByclienteIdContaining(Long id);
	
	List<Prestamo> findAllByclienteId(Long id);
 }
