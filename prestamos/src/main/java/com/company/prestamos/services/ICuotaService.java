package com.company.prestamos.services;

import org.springframework.http.ResponseEntity;

import com.company.prestamos.model.Cuota;
import com.company.prestamos.response.CuotaResponseRest;
import com.company.prestamos.response.PrestamoResponseRest;

public interface ICuotaService {
	public ResponseEntity<CuotaResponseRest> save(Cuota cuota, Long prestamoId);
	public ResponseEntity<CuotaResponseRest> searchByPrestamoId(Long id);

}
