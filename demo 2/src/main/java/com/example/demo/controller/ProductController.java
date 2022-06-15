package com.example.demo.controller;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.products;
import com.example.demo.repository.productrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private com.example.demo.repository.productrepository productrepository;

    @GetMapping
    public List<products> getAllproducts(){
        return productrepository.findAll();
    }

    // build create employee REST API
    @PostMapping
    public products createproducts(@RequestBody products products) {
        return productrepository.save(products);
    }

    // build get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<products> getproductsById(@PathVariable  long id){
        products products = productrepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("products not exist with id:" + id));
        return ResponseEntity.ok(products);
    }

    // build update products REST API
    @PutMapping("{id}")
    public ResponseEntity<products> updateproduct(@PathVariable long id,@RequestBody products productDetails) {
        products updateproduct = productrepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        updateproduct.setName(productDetails.getName());
        updateproduct.setProduct_id(productDetails.getProduct_id());
        updateproduct.setBrand(productDetails.getBrand());
        updateproduct.setPrice(productDetails.getPrice());

        productrepository.save(productDetails);

        return ResponseEntity.ok(updateproduct);
    }

    // build delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteproduct(@PathVariable long id){

        products products = productrepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));

        productrepository.delete(products);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}