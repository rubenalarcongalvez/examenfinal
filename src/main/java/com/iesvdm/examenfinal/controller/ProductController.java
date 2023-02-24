package com.iesvdm.examenfinal.controller;

import com.iesvdm.examenfinal.domain.Product;
import com.iesvdm.examenfinal.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:4200") //No hace falta
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //    Ruta sin rutas que contengan esos parámetros
    @GetMapping(value = {"", "/"}, params = {"!buscar", "!ordenar", "!pagina", "!tamanio"})
    public List<Product> all() {
        return this.productService.all();
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam("buscar") Optional<String> buscarOptional, @RequestParam("ordenar") Optional<String> ordenarOptional, @RequestParam("pagina") int pagina, @RequestParam("tamanio") int tamanio) {

        Map<String, Object> responseall = this.productService.all(buscarOptional, ordenarOptional, pagina, tamanio);

        return ResponseEntity.ok(responseall);
    }

    @PostMapping({"", "/"})
    public Product newProduct(@RequestBody Product product) {
        return this.productService.save(product);
    }

    @GetMapping("/{id}")
    public Product one(@PathVariable("id") Long id) {
        return this.productService.one(id);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return this.productService.replace(id, product);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        this.productService.delete(id);
    }

}
