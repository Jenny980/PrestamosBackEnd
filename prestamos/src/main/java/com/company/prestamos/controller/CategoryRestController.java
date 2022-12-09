package com.company.prestamos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.prestamos.model.Category;
import com.company.prestamos.response.CategoryResponseRest;
import com.company.prestamos.services.ICategoryService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v2")
public class CategoryRestController {
	
	@Autowired
	private ICategoryService service;
	
	/**
	 * Get categories
	 * @return
	 */
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponseRest> searchCategories(){
		ResponseEntity<CategoryResponseRest> response = service.search();
		return response;
	}
	/**
	 * Get category by id
	 * @param id
	 * @return
	 */
	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> searchCategoriesById(@PathVariable Long id){
		ResponseEntity<CategoryResponseRest> response = service.searchById(id);
		return response;
	}
	
	/**
	 * Post category
	 * @param category
	 * @return
	 */
	@PostMapping("/categories")
	public ResponseEntity<CategoryResponseRest> save(@RequestBody Category category){
		ResponseEntity<CategoryResponseRest> response = service.save(category);
		return response;
	}
	
	/**
	 * Update category
	 * @param category
	 * @param id
	 * @return
	 */
	@PutMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> update(@RequestBody Category category, @PathVariable Long id){
		ResponseEntity<CategoryResponseRest> response = service.update(category, id);
		return response;
	}
	
	/**
	 * Delete category
	 * @param id
	 * @return
	 */
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> delete(@PathVariable Long id){
		ResponseEntity<CategoryResponseRest> response = service.deleteById(id);
		return response;
	}

}
