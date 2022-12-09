package com.company.prestamos.response;

import java.util.List;

import com.company.prestamos.model.Cliente;
import com.company.prestamos.model.Prestamo;

import lombok.Data;

@Data
public class PrestamoResponse {
	private List<Prestamo> prestamos;
}
