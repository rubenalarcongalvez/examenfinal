package com.iesvdm.examenfinal.service;

import com.iesvdm.examenfinal.domain.Product;
import com.iesvdm.examenfinal.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> all() {
        return this.productRepository.findAll();
    }

    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    public Product one(Long id) {
        return this.productRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public Product replace(Long id, Product product) {
        return this.productRepository.findById(id).map( p -> (id.equals(product.getId())  ?
                        this.productRepository.save(product) : null))
                .orElseThrow(RuntimeException::new);

    }

    public void delete(Long id) {
        this.productRepository.findById(id).map(p -> {this.productRepository.delete(p);
                    return p;})
                .orElseThrow(RuntimeException::new);
    }

    //Paginación con filtros
    public Map<String, Object> all(Optional<String> optionalBuscar, Optional<String> optionalOrdenar, int pagina, int tamanio) {

        //Por defecto será descending
        Pageable paginado = PageRequest.of(pagina, tamanio, Sort.by("name").descending());
        Page<Product> pageAll;

        if (optionalOrdenar.isPresent() && optionalOrdenar.equals(Optional.of("asc"))) {
            paginado = PageRequest.of(pagina, tamanio, Sort.by("name").ascending());
        }

        if (optionalBuscar.isPresent()) {
            pageAll = this.productRepository.findAllByNameContainingIgnoreCase(optionalBuscar.get() , paginado);
        } else {
            pageAll = this.productRepository.findAll(paginado);
        }

        Map<String, Object> response = new HashMap<>();

        response.put("products", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;
    }

}