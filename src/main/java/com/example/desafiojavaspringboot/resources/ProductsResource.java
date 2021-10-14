package com.example.desafiojavaspringboot.resources;

import com.example.desafiojavaspringboot.domain.Products;
import com.example.desafiojavaspringboot.repositories.SearchPath;
import com.example.desafiojavaspringboot.services.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductsResource {

    @Autowired
    private final ProductsService service;

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Products> create(@Valid @RequestBody Products product){

        this.service.createProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @RequestMapping(value="/products", method = RequestMethod.GET)
    public ResponseEntity<List<Products>> findAllProducts(){

        List<Products> products = this.service.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<Products> findProduct(@PathVariable("id") Integer id){
        Optional<Products> product = this.service.findProduct(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/products/search", method = RequestMethod.GET)
    public ResponseEntity<List<Products>> findProductsWithParams(@RequestParam(value = "min_price", required = false) BigDecimal min_price, @RequestParam(value = "max_price", required = false) BigDecimal max_price, @RequestParam(value = "q", required = false) String q){
        List<Products> products = this.service.search(q, min_price, max_price);
        return new ResponseEntity<>(products, HttpStatus.OK);

        }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Products> updateProduct(@PathVariable Integer id, @Valid @RequestBody Products product) {
        this.service.saveProduct(id, product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        Optional<Products> product = this.service.findProduct(id);
        return product.map(value -> {
            this.service.deleteProducts(value);
            return new ResponseEntity<>(HttpStatus.OK);}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}

