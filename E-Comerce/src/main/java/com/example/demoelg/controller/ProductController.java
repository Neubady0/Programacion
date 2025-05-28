package com.example.demoelg.controller;

import com.example.demoelg.dto.ProductCreateDTO;
import com.example.demoelg.dto.ProductDTO;
import com.example.demoelg.model.Category;
import com.example.demoelg.model.Product;
import com.example.demoelg.service.CategoryService;
import com.example.demoelg.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    // Crear producto usando DTO de entrada (ProductoCreateDTO)
    @PostMapping
    public ResponseEntity<ProductDTO> makeProducts(@RequestBody ProductCreateDTO dto) {
        // Convertir DTO -> Entidad
        Product p = new Product();
        p.setName(dto.getName());
        p.setPrice(dto.getPrice().longValue());
        p.setStock(dto.getStock());
        p.setFabricationCost(dto.getFabricationCost());
        // Asignar categoría si se indica
        if (dto.getCategoryId() != null) {
            Category c =
                    categoryService.findCategory(dto.getCategoryId());
            p.setCategory(c);
        }
        Product save = productService.saveProduct(p);
        // Convertir Entidad -> DTO
        ProductDTO answer = convertAProductoDTO(save);
        return new ResponseEntity<>(answer, HttpStatus.CREATED);
    }
    // Listar productos (mostrando DTO sin stock ni costeFabricacion)
    @GetMapping
    public ResponseEntity<List<ProductDTO>> listProducts() {
        List<Product> products = productService.listProducts();
        List<ProductDTO> listDTO = new ArrayList<>();
        for (Product p : products) {
            listDTO.add(convertAProductoDTO(p));
        }
        return ResponseEntity.ok(listDTO);
    }
    // Obtener un producto por ID con DTO
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long
                                                           id) {
        Product p = productService.findProduct(id);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        ProductDTO dto = convertAProductoDTO(p);
        return ResponseEntity.ok(dto);
    }
    // Actualizar un producto usando DTO de entrada
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductCreateDTO dto) {
        Product existent = productService.findProduct(id);
        if (existent == null) {
            return ResponseEntity.notFound().build();
        }
        // Actualizar campos
        existent.setName(dto.getName());
        existent.setPrice(dto.getPrice().longValue());
        existent.setStock(dto.getStock());
        existent.setFabricationCost(dto.getFabricationCost());
        if (dto.getCategoryId() != null) {
            Category c = categoryService.findCategory(dto.getCategoryId());
            existent.setCategory(c);
        } else {
            existent.setCategory(null);
        }
        Product updated =
                productService.saveProduct(existent);
        ProductDTO answer = convertAProductoDTO(updated);
        return ResponseEntity.ok(answer);
    }
    // Borrar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcuct(@PathVariable Long id) {
        boolean delete = productService.deleteProduct(id);
        if (delete) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    // ----------------------------------------------
    // Convertir Entidad -> DTO
    private ProductDTO convertAProductoDTO(Product p) {
        ProductDTO dto = new ProductDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setPrice(p.getPrice().doubleValue());
        if (p.getCategory() != null) {
            dto.setCategoryName(p.getCategory().getName());
        }
        return dto;
    }
}
