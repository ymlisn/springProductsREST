package com.example.desafiojavaspringboot.repositories;

import com.example.desafiojavaspringboot.domain.Products;

import java.math.BigDecimal;

public class SearchPath {

    public boolean queryWithParamsMethod(Products product, String q, BigDecimal min_price, BigDecimal max_price){

        return (product.getDescription().contains(q == null ? product.getDescription() : q) || product.getName().contains(q == null ? product.getName() : q))
                && (product.getPrice().compareTo(min_price == null ? product.getPrice() : min_price) >= 0)
                && (product.getPrice().compareTo(max_price == null ? product.getPrice() : max_price) <= 0);

    }

}
