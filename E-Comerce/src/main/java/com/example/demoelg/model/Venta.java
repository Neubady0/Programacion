package com.example.demoelg.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "venta")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Venta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long id;

    private String estado;        // p.ej. PENDIENTE, PAGADA, ENVIADA…
    private Long precioFinal;

    // Muchas ventas pertenecen a un usuario
    @ManyToOne @JoinColumn(name = "id_usuario")
    private User user;

    // Muchas ventas pertenecen a un carrito
    @ManyToOne @JoinColumn(name = "id_carrito")
    private Cart carrito;

    // Una venta tiene un método de pago
    @OneToOne(mappedBy = "venta", cascade = CascadeType.ALL)
    private MetodoPago metodoPago;
}