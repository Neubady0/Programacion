package com.example.demoelg.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "metodo_pago")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MetodoPago {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long id;

    private String tipoTarjeta;
    private String nombreTitular;
    private Long numeroTarjeta;
    private Integer fechaCaducidad;   // simplificado YYYYMM

    // === Relaciones ===
    @OneToOne
    @JoinColumn(name = "id_venta", unique = true)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User user;
}
