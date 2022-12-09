package com.company.prestamos.response;

import java.util.List;
import com.company.prestamos.model.Category;

import lombok.Data;

@Data
public class CategoryResponse {
	
	private List<Category> category;

}
