package com.example.desafiojavaspringboot.resources;


import com.example.desafiojavaspringboot.domain.Products;
import com.example.desafiojavaspringboot.services.ProductsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductResourceTest {
    @InjectMocks
    private ProductsResource productsResource;

    @Test
    void createProductTest_Sucess(){
        ProductsService productsService = Mockito.mock(ProductsService.class);
        this.productsResource = new ProductsResource(productsService);

        Products product = new Products();
        ResponseEntity<Products> httpStatus = this.productsResource.create(product);

        assertEquals(HttpStatus.CREATED, httpStatus.getStatusCode());
    }

    @Test
    void updateProductTest_Sucess(){

        ProductsService productsService = Mockito.mock(ProductsService.class);
        this.productsResource = new ProductsResource(productsService);


        Products product = new Products();
        ResponseEntity<Products> httpStatusCode = this.productsResource.updateProduct(1, product);

        assertEquals(HttpStatus.OK, httpStatusCode.getStatusCode());
    }

    @Test
    void findProductTest_Sucess(){

        Products product = new Products();
        Optional<Products> productToReturn = Optional.of(product);
        ProductsService productsService = Mockito.mock(ProductsService.class);
        this.productsResource = new ProductsResource(productsService);
        Mockito.doReturn(productToReturn).when(productsService).findProduct(1);

        ResponseEntity<Products> httpStatusCode = this.productsResource.findProduct(1);

        assertEquals(HttpStatus.OK, httpStatusCode.getStatusCode());
    }

    @Test
    void findAllProducts_Test(){

        Products product = new Products();
        List<Products> products = new ArrayList<>();
        products.add(product);
        ProductsService productsService = Mockito.mock(ProductsService.class);
        this.productsResource = new ProductsResource(productsService);
        Mockito.doReturn(products).when(productsService).findAll();

        ResponseEntity<List<Products>> httpStatusCode = this.productsResource.findAllProducts();

        assertEquals(HttpStatus.OK, httpStatusCode.getStatusCode());
    }

    @Test
    void deleteProductTest(){

        Products product = new Products();
        Optional<Products> productToDelete = Optional.of(product);
        ProductsService productsService = Mockito.mock(ProductsService.class);
        this.productsResource = new ProductsResource(productsService);
        Mockito.doReturn(productToDelete).when(productsService).findProduct(1);
        Mockito.doNothing().when(productsService).deleteProducts(product);

        ResponseEntity<?> httpStatusCode = this.productsResource.deleteProduct(1);

        assertEquals(HttpStatus.OK, httpStatusCode.getStatusCode());
    }

}
