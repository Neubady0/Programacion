package com.example.demoelg.dto;

public class ProductCreateDTO {
    private String name;
    private Double price;
    private Integer stock;
    private Double fabricationCost;
    private Long categoryId; // para saber a qué categoría asociar
    // Getters y Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getFabricationCost() {
        return fabricationCost;
    }

    public void setFabricationCost(Double fabricationCost) {
        this.fabricationCost = fabricationCost;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}

