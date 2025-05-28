package com.example.demoelg.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Entity
@Table(name = "carrito")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // campo no estaba en el ER, lo añadimos para PK.

    private String name;
    private Double price;

    @Enumerated(EnumType.STRING)
    private EstadoLista list;   // enum para estado / tipo de lista.

    @OneToOne
    @JoinColumn(name = "id_usuario", unique = true)
    private User user;

    // Ventas asociadas al carrito
    @OneToMany(mappedBy = "carrito")
    private List<Venta> ventas;

    public enum EstadoLista { WISHLIST, ACTIVO, COMPRADO }
}