package com.prac.radis.contoller;

import com.prac.radis.entity.Product;
import com.prac.radis.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService =productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
     return this.productService.getProductById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProducts(){
        return this.productService.getProducts();
    }

    @PostMapping("/save")
    public String saveProduct(@RequestBody Product product){
       return this.productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public String saveProduct(@PathVariable Long id,@RequestBody Product product){
       return this.productService.editProduct(id,product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        return this.productService.deleteProduct(id);
    }
}
