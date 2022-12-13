package com.company.prestamos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.prestamos.dao.IClienteDao;
import com.company.prestamos.dao.ICuotaDao;
import com.company.prestamos.dao.IPrestamoDao;
import com.company.prestamos.model.Cliente;
import com.company.prestamos.model.Cuota;
import com.company.prestamos.model.Prestamo;
import com.company.prestamos.response.CuotaResponseRest;
import com.company.prestamos.response.PrestamoResponseRest;

@Service
public class CuotaServiceImpl implements ICuotaService{
	
	private IPrestamoDao prestamoDao;
	private ICuotaDao cuotaDao;
	

	public CuotaServiceImpl(IPrestamoDao prestamoDao, ICuotaDao cuotaDao) {
		super();
		this.prestamoDao = prestamoDao;
		this.cuotaDao = cuotaDao;
	}

	@Override
	@Transactional
	public ResponseEntity<CuotaResponseRest> save(Cuota cuota, Long prestamoId) {
		CuotaResponseRest response = new CuotaResponseRest();
		List<Cuota> list = new ArrayList<>();
		try {
			Optional<Prestamo> prestamo = prestamoDao.findById(prestamoId);
			if(prestamo.isPresent()) {
				cuota.setPrestamo(prestamo.get());
			} else {
				response.setMetadata("Respuesta nook", "-1", "Cuota no encontrada");
				return new ResponseEntity<CuotaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			Cuota cuotaSaved = cuotaDao.save(cuota);
			
			if(cuotaSaved != null) {
				list.add(cuotaSaved);
				response.getCuotaResponse().setCuotas(list);
				response.setMetadata("Respuesta ok", "00", "Cuota guardada");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Cuota no guardado");
				return new ResponseEntity<CuotaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al guardar cuota");
			e.getStackTrace();
			return new ResponseEntity<CuotaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<CuotaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CuotaResponseRest> searchByPrestamoId(Long id) {
		CuotaResponseRest response = new CuotaResponseRest();
		List<Cuota> list = new ArrayList<>();
		List<Cuota> listAux = new ArrayList<>();
		try {
			
			listAux = cuotaDao.findAllByprestamoId(id);
			
			if(listAux.size() > 0) {
				
				listAux.stream().forEach((p) -> {
					list.add(p);
				});
				
				response.getCuotaResponse().setCuotas(list);
				response.setMetadata("Respuesta ok", "00", "Cuotas por id encontrado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Cuotas por id no encontrado");
				return new ResponseEntity<CuotaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al buscar por prestamoId");
			e.getStackTrace();
			return new ResponseEntity<CuotaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<CuotaResponseRest>(response, HttpStatus.OK);
	}

}
