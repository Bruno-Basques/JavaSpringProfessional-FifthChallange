package com.brunobasques_jsp.fifth_challange.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.brunobasques_jsp.fifth_challange.entities.Category;
import com.brunobasques_jsp.fifth_challange.entities.Product;
import com.brunobasques_jsp.fifth_challange.services.exceptions.DatabaseException;
import com.brunobasques_jsp.fifth_challange.services.exceptions.ResourceNotFoundException;
import com.brunobasques_jsp.fifth_challange.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        Product product = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return product;
    }

    @Transactional(readOnly = true)
    public Page<Product> findAll(String name, Pageable pageable) {
        Page<Product> result = repository.searchByName(name, pageable);
        return result;
    }

    @Transactional
    public Product insert(Product product) {
        Product newlySavedProduct = repository.save(product);
        return newlySavedProduct;
    }

    @Transactional
    public Product update(Long id, Product product) {
        try {
            Product newlyUpdatedProduct = repository.getReferenceById(id);
            copySourceProductToDestinationProduct(product, newlyUpdatedProduct);
            newlyUpdatedProduct = repository.save(newlyUpdatedProduct);
            return newlyUpdatedProduct;
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
    	if (!repository.existsById(id)) {
    		throw new ResourceNotFoundException("Recurso não encontrado");
    	}
    	try {
            repository.deleteById(id);    		
    	}
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copySourceProductToDestinationProduct(Product srcProduct, Product destProduct) {
    	destProduct.setName(srcProduct.getName());
    	destProduct.setDescription(srcProduct.getDescription());
    	destProduct.setPrice(srcProduct.getPrice());
    	destProduct.setImgUrl(srcProduct.getImgUrl());
        
    	destProduct.getCategories().clear();
        for (Category srcCat : srcProduct.getCategories()) {
        	Category destCat = new Category();
        	destCat.setId(srcCat.getId());
        	destProduct.getCategories().add(destCat);
        }
    }
}
