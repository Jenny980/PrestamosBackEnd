package com.company.prestamos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.prestamos.dao.IClienteDao;
import com.company.prestamos.dao.IPrestamoDao;
import com.company.prestamos.model.Cliente;
import com.company.prestamos.model.Prestamo;
import com.company.prestamos.response.ClienteResponseRest;
import com.company.prestamos.response.PrestamoResponseRest;

@Service
public class PrestamoServiceImpl implements IPrestamoService{
	
	private IClienteDao clienteDao;
	private IPrestamoDao prestamoDao;
	
	public PrestamoServiceImpl(IClienteDao clienteDao, IPrestamoDao prestamoDao) {
		super();
		this.clienteDao = clienteDao;
		this.prestamoDao = prestamoDao;
	}

	@Override
	@Transactional
	public ResponseEntity<PrestamoResponseRest> save(Prestamo prestamo, Long clienteId) {
		PrestamoResponseRest response = new PrestamoResponseRest();
		List<Prestamo> list = new ArrayList<>();
		try {
			Optional<Cliente> cliente = clienteDao.findById(clienteId);
			if(cliente.isPresent()) {
				prestamo.setCliente(cliente.get());
			} else {
				response.setMetadata("Respuesta nook", "-1", "Prestamo no encontrado");
				return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			Prestamo prestamoSaved = prestamoDao.save(prestamo);
			
			if(prestamoSaved != null) {
				list.add(prestamoSaved);
				response.getPrestamoResponse().setPrestamos(list);
				response.setMetadata("Respuesta ok", "00", "Prestamo guardado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Prestamo no guardado");
				return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al guardar prestamo");
			e.getStackTrace();
			return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PrestamoResponseRest> searchById(Long id) {
		PrestamoResponseRest response = new PrestamoResponseRest();
		List<Prestamo> list = new ArrayList<>();
		try {
			Optional<Prestamo> prestamo = prestamoDao.findById(id);
			
			if(prestamo.isPresent()) {
				list.add(prestamo.get());
				response.getPrestamoResponse().setPrestamos(list);
				response.setMetadata("Respuesta ok", "00", "Prestamo encontrado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Prestamo no encontrado");
				return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al consultar por id");
			e.getStackTrace();
			return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PrestamoResponseRest> search() {
		PrestamoResponseRest response = new PrestamoResponseRest();
		try {
			List<Prestamo> prestamo = (List<Prestamo>) prestamoDao.findAll();
			response.getPrestamoResponse().setPrestamos(prestamo);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PrestamoResponseRest> searchByPeriodoPago(String periodoPago) {
		PrestamoResponseRest response = new PrestamoResponseRest();
		List<Prestamo> list = new ArrayList<>();
		List<Prestamo> listAux = new ArrayList<>();
		try {
			
			listAux = prestamoDao.findByperiodoPagoContainingIgnoreCase(periodoPago);
			
			if(listAux.size() > 0) {
				
				listAux.stream().forEach((p) -> {
					list.add(p);
				});
				
				response.getPrestamoResponse().setPrestamos(list);
				response.setMetadata("Respuesta ok", "00", "Prestamos encontrado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Prestamos no encontrado");
				return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al buscar por periodoPago");
			e.getStackTrace();
			return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PrestamoResponseRest> searchByClienteId(Long id) {
		PrestamoResponseRest response = new PrestamoResponseRest();
		List<Prestamo> list = new ArrayList<>();
		List<Prestamo> listAux = new ArrayList<>();
		try {
			
			listAux = prestamoDao.findAllByclienteId(id);
			
			if(listAux.size() > 0) {
				
				listAux.stream().forEach((p) -> {
					list.add(p);
				});
				
				response.getPrestamoResponse().setPrestamos(list);
				response.setMetadata("Respuesta ok", "00", "Prestamos por id encontrado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Prestamos por id no encontrado");
				return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al buscar por clienteId");
			e.getStackTrace();
			return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<PrestamoResponseRest> update(Prestamo prestamo, Long clienteId, Long id) {
		PrestamoResponseRest response = new PrestamoResponseRest();
		List<Prestamo> list = new ArrayList<>();
		try {
			Optional<Cliente> cliente = clienteDao.findById(clienteId);
			if(cliente.isPresent()) {
				prestamo.setCliente(cliente.get());
			} else {
				response.setMetadata("Respuesta nook", "-1", "Prestamo no encontrado");
				return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			Optional <Prestamo> prestamoSearch = prestamoDao.findById(id);
			
			if(prestamoSearch.isPresent()) {
				prestamoSearch.get().setCredito(prestamo.getCredito());
				prestamoSearch.get().setPeriodoPago(prestamo.getPeriodoPago());
				prestamoSearch.get().setNpagos(prestamo.getNpagos());
				prestamoSearch.get().setValorCuota(prestamo.getValorCuota());
				prestamoSearch.get().setDebe(prestamo.getDebe());
				prestamoSearch.get().setCliente(prestamo.getCliente());
				
				Prestamo prestamoToUpdate = prestamoDao.save(prestamoSearch.get());
				
				if(prestamoToUpdate != null) {
					list.add(prestamoToUpdate);
					response.getPrestamoResponse().setPrestamos(list);
					response.setMetadata("Respuesta ok", "00", "Prestamo actualizado");
				} else {
					response.setMetadata("Respuesta nook", "-1", "Prestamo no actualizado");
					return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
				
			} else {
				response.setMetadata("Respuesta nook", "-1", "Prestamo no encontrado");
				return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al actualizar prestamo");
			e.getStackTrace();
			return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<PrestamoResponseRest> deleteById(Long id) {
		PrestamoResponseRest response = new PrestamoResponseRest();

		try {
			prestamoDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Prestamo eliminado");
			
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al eliminar prestamo");
			e.getStackTrace();
			return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<PrestamoResponseRest>(response, HttpStatus.OK);
	}

}
