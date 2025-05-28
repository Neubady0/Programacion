package com.example.demoelg.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producto")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;

    private Integer stock;

    @Enumerated(EnumType.STRING)
    private Talla size;   // Enum interna abajo

    private String name;
    private Long price;

    @Column(columnDefinition = "TEXT")
    private String description;

    // N:1 -> Una categoría
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Category category;

    public void setFabricationCost(Double fabricationCost) {
    }

    // ===== Enum para tallas =====
    public enum Talla { XS, S, M, L, XL }
}