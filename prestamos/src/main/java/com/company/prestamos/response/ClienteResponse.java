package com.company.prestamos.response;

import java.util.List;
import com.company.prestamos.model.Cliente;
import lombok.Data;

@Data
public class ClienteResponse {
	
	private List<Cliente> cliente; 

}
