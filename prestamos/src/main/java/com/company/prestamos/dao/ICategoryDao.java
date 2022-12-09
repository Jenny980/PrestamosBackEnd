package com.company.prestamos.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.prestamos.model.Category;

public interface ICategoryDao extends CrudRepository<Category, Long>{

}
