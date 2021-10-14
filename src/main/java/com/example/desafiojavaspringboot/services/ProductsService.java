package com.example.desafiojavaspringboot.services;

import com.example.desafiojavaspringboot.domain.Products;
import com.example.desafiojavaspringboot.repositories.ProductsRepository;
import com.example.desafiojavaspringboot.repositories.SearchPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository repository;

    public Optional<Products> findProduct(Integer id) {
        return this.repository.findById(id);
    }

    public void saveProduct(Integer id, Products product) {

        this.repository.findById(id).map(oldProduct -> { product.setId(oldProduct.getId());
            this.repository.save(product);
                    return oldProduct;
                    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    public List<Products> findAll()  { return this.repository.findAll(); }

    public void createProduct(Products product)  { this.repository.save(product); }

    public void deleteProducts(Products product)  {this.repository.delete(product);}

    public List<Products> search(String q, BigDecimal min_price, BigDecimal max_price) {

        List<Products> allProductsFiltered = new ArrayList<Products>();

        if(q == null && max_price == null && min_price == null){
            return repository.findAll();
        }

        else {
            SearchPath searchPath = new SearchPath();

            for (Products products : repository.findAll()) {
                if(searchPath.queryWithParamsMethod(products, q, min_price, max_price)){

                    allProductsFiltered.add(products);
                }
            }

        }

        return allProductsFiltered;
    }


    }


