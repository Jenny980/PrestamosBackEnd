package com.company.prestamos.services;

import org.springframework.http.ResponseEntity;

import com.company.prestamos.model.Category;
import com.company.prestamos.response.CategoryResponseRest;

public interface ICategoryService {
	
	public ResponseEntity<CategoryResponseRest> search();
	public ResponseEntity<CategoryResponseRest> searchById(Long id);
	public ResponseEntity<CategoryResponseRest> save(Category category);
	public ResponseEntity<CategoryResponseRest> update(Category category, Long id);
	public ResponseEntity<CategoryResponseRest> deleteById(Long id);
}
