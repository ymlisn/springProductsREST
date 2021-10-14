package com.example.desafiojavaspringboot.services;

import com.example.desafiojavaspringboot.domain.Products;
import com.example.desafiojavaspringboot.errors.InvalidRequestException;
import com.example.desafiojavaspringboot.errors.ResourceNotFoundException;
import com.example.desafiojavaspringboot.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository repository;

    public ResponseEntity<Products> findProduct(Integer id){
        Optional<Products> product = repository.findById(id);

        if(product.isPresent())
            return new ResponseEntity<Products>(product.get(),HttpStatus.OK);
        else
            throw new ResourceNotFoundException("Product not found");

    }

    public ResponseEntity<Products> saveProduct(Integer id, Products newProduct){
        Optional<Products> oldProduct = repository.findById(id);

        if(oldProduct.isPresent()){
            Products product = oldProduct.get();
            product.setName(newProduct.getName());
            product.setDescription(newProduct.getDescription());
            product.setPrice(newProduct.getPrice());

            parametersVerification(product);
            repository.save(product);

            return new ResponseEntity<Products>(product,HttpStatus.OK);

        }
        else
            throw new ResourceNotFoundException("Product not found");
    }

    public List<Products> findAll() {

        return repository.findAll();

    }

    public ResponseEntity<Products> createProduct(Products product) {

            parametersVerification(product);
            repository.save(product);
            return new ResponseEntity<Products>(product, HttpStatus.OK);

    }

    public ResponseEntity<Products> deleteProducts(Integer id) {
        Optional<Products> product = repository.findById(id);

        if(product.isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            throw new ResourceNotFoundException("Product not found");

    }

    private void parametersVerification(Products product) {

        if(product.getName() == null || product.getDescription() == null || product.getPrice() == null )
            throw new InvalidRequestException("Parameters can't be null");

        if(product.getPrice() < 0)
            throw new InvalidRequestException("Price can't be negative");

    }
    }


