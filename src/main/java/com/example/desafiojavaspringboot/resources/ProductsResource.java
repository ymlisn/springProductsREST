package com.example.desafiojavaspringboot.resources;

import com.example.desafiojavaspringboot.domain.Products;
import com.example.desafiojavaspringboot.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductsResource {

    @Autowired
    private ProductsService service;

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Products product){

        ResponseEntity<Products> products = service.createProduct(product);
        return ResponseEntity.ok().body(products);
    }

    @RequestMapping(value="/products", method = RequestMethod.GET)
    public List<Products> findAllProducts(){

        return service.findAll();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findProduct(@PathVariable Integer id){
        ResponseEntity<Products> product = service.findProduct(id);

        return ResponseEntity.ok().body(product);
    }

    @RequestMapping(value = "/products/search", method = RequestMethod.GET)
    public List<Products> findProductsWithParams(@RequestParam(value = "min_price", required = false) Double min_price, @RequestParam(value = "max_price", required = false) Double max_price, @RequestParam(value = "q", required = false) String q){

        List<Products> allProducts = service.findAll();
        List<Products> allProductsFiltered = new ArrayList<Products>();

        if(q == null && max_price == null && min_price == null){
            return allProducts;
        }

        else {
            for (Products products : allProducts) {
                if((products.getDescription().contains(q == null ? products.getDescription() : q))
                   && (products.getPrice() >= (min_price == null ? products.getPrice() : min_price))
                   && (products.getPrice() <= (max_price == null ? products.getPrice() : max_price))){

                        allProductsFiltered.add(products);
                    }
                }

            }

            return allProductsFiltered;
        }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> changeProduct(@PathVariable(value = "id") Integer id, @RequestBody Products newProduct){
        ResponseEntity<Products> product = service.saveProduct(id, newProduct);

        return ResponseEntity.ok().body(product);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        ResponseEntity<Products> product = service.deleteProducts(id);

        return ResponseEntity.ok(HttpStatus.OK);

    }

}

