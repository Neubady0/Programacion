package com.example.demoelg.service;

import com.example.demoelg.model.Product;
import com.example.demoelg.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> listProducts() {
        return productRepository.findAll();
    }
    public Product findProduct(Long id) {
        // Buscar por ID
        // Retorna null si no existe (evitamos Optional en este ejemplo básico)
        Product p = productRepository.findById(id).orElse(null);
        return p;
    }
    public Product saveProduct (Product p) {
        // Inserta o actualiza (si p tiene un ID existente)
        return productRepository.save(p);
    }
    public boolean deleteProduct (Long id) {
        // Verifica si existe
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


