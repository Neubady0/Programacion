package com.example.demoelg.controller;


import com.example.demoelg.dto.CategoryDTO;
import com.example.demoelg.model.Category;
import com.example.demoelg.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO dto) {
        // DTO -> Entidad
        Category c = new Category();
        c.setName(dto.getName());
        c.setDescription(dto.getDescription());
        Category save = categoryService.saveCategory(c);
        // Entidad -> DTO
        CategoryDTO answer = convertACategoryDTO(save);
        return new ResponseEntity<>(answer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listCategories() {
        List<Category> list = categoryService.listCategories();
        List<CategoryDTO> dtos = new ArrayList<>();
        for (Category c : list) {
            dtos.add(convertACategoryDTO(c));
        }
        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long
                                                             id) {
        Category c = categoryService.findCategory(id);
        if (c == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertACategoryDTO(c));
    }
    @PostMapping
    public Category makeCategory(@RequestBody Category c) {
        return categoryService.saveCategory(c);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id)
    {
        boolean deleted = categoryService.deleteCategory(id);
        if (deleted) {
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.notFound().build();
    }

    // ---------------------------------------
    private CategoryDTO convertACategoryDTO(Category c) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setDescription(c.getDescription());
        return dto;
    }

}