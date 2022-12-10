package com.company.prestamos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.prestamos.dao.IClienteDao;
import com.company.prestamos.model.Category;
import com.company.prestamos.model.Cliente;
import com.company.prestamos.model.Prestamo;
import com.company.prestamos.response.CategoryResponseRest;
import com.company.prestamos.response.ClienteResponseRest;
import com.company.prestamos.response.PrestamoResponseRest;

@Service
public class ClienteServiceImpl implements IClienteService{
	
	@Autowired
	private IClienteDao clienteDao;

	@Override
	//@Transactional(readOnly)
	public ResponseEntity<ClienteResponseRest> search() {
		ClienteResponseRest response = new ClienteResponseRest();
		try {
			List<Cliente> cliente = (List<Cliente>) clienteDao.findAll();
			response.getClienteResponse().setCliente(cliente);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
	}

	@Override
	//@Transactional(readOnly = true)
	public ResponseEntity<ClienteResponseRest> searchById(Long id) {
		ClienteResponseRest response = new ClienteResponseRest();
		List<Cliente> list = new ArrayList<>();
		try {
			Optional<Cliente> cliente = clienteDao.findById(id);
			
			if(cliente.isPresent()) {
				list.add(cliente.get());
				response.getClienteResponse().setCliente(list);
				response.setMetadata("Respuesta ok", "00", "Cliente encontrado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Cliente no encontrado");
				return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al consultar por id");
			e.getStackTrace();
			return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ClienteResponseRest> save(Cliente cliente) {
		ClienteResponseRest response = new ClienteResponseRest();
		List<Cliente> list = new ArrayList<>();
		try {
			Cliente clienteSave = clienteDao.save(cliente);
			if(clienteSave != null) {
				list.add(clienteSave);
				response.getClienteResponse().setCliente(list);
				response.setMetadata("Respuesta ok", "00", "Cliente guardado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Cliente no guardado");
				return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al guardar cliente");
			e.getStackTrace();
			return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ClienteResponseRest> update(Cliente cliente, Long id) {
		ClienteResponseRest response = new ClienteResponseRest();
		List<Cliente> list = new ArrayList<>();
		try {
			Optional<Cliente> clienteSearch = clienteDao.findById(id);
			if(clienteSearch.isPresent()) {
				clienteSearch.get().setNombre(cliente.getNombre());
				clienteSearch.get().setApellido(cliente.getApellido());
				clienteSearch.get().setBarrio(cliente.getBarrio());
				clienteSearch.get().setCc(cliente.getCc());
				clienteSearch.get().setDireccion(cliente.getDireccion());
				clienteSearch.get().setTelefono(cliente.getTelefono());
				
				Cliente clienteToUpdate = clienteDao.save(clienteSearch.get());
				
				if(clienteToUpdate != null) {
					list.add(clienteToUpdate);
					response.getClienteResponse().setCliente(list);
					response.setMetadata("Respuesta ok", "00", "Cliente actualizado");
				} else {
					response.setMetadata("Respuesta nook", "-1", "Cliente no actualizado");
					return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}else {
				response.setMetadata("Respuesta nook", "-1", "Cliente no encontrado");
				return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al actualizar cliente");
			e.getStackTrace();
			return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ClienteResponseRest> deleteById(Long id) {
		ClienteResponseRest response = new ClienteResponseRest();
		try {
			clienteDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Cliente eliminado");
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ClienteResponseRest> searchByNombre(String nombre) {
		ClienteResponseRest response = new ClienteResponseRest();
		List<Cliente> list = new ArrayList<>();
		List<Cliente> listAux = new ArrayList<>();
		try {
			
			listAux = clienteDao.findBynombreContainingIgnoreCase(nombre);
			
			if(listAux.size() > 0) {
				
				listAux.stream().forEach((p) -> {
					list.add(p);
				});
				
				response.getClienteResponse().setCliente(list);
				response.setMetadata("Respuesta ok", "00", "Cliente encontrado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Cliente no encontrado");
				return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al buscar por nombre");
			e.getStackTrace();
			return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
	}

}
