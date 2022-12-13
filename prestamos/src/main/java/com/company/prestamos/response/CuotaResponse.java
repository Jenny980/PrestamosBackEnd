package com.company.prestamos.response;

import java.util.List;

import com.company.prestamos.model.Cuota;

import lombok.Data;

@Data
public class CuotaResponse {
	private List<Cuota> cuotas;

}
