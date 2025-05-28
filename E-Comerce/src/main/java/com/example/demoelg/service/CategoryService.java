package com.example.demoelg.service;

import com.example.demoelg.model.Category;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    // Método para listar categorías
    public List<Category> listCategories() {
        List<Category> categories = new ArrayList<>();
        // Simulación de datos o implementación lógica
        return categories;
    }

    // Método para buscar una categoría por ID
    public Category findCategory(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo.");
        }
        // Simulación lógica o lógica de búsqueda
        return new Category(); // Suponiendo que existe un objeto Category.
    }

    // Método para guardar una categoría
    public Category saveCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("La categoría no puede ser nula.");
        }
        // Devuelve la misma categoría como un simple ejemplo.
        return category;
    }

    // Método para eliminar una categoría
    public boolean deleteCategory(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo.");
        }
        // Lógica simulada para eliminar.
        return true; // Suponiendo que la operación se realizó correctamente.
    }
}