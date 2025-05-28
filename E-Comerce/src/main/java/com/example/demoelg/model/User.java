package com.example.demoelg.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    private String nombre;
    private String password;
    private String email;
    private Integer age;
    private Integer cp;
    private Integer phone;
    private String location;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // Un usuario tiene muchas ventas
    @OneToMany(mappedBy = "usuario")
    private List<Venta> ventas;

    // Un usuario posee un carrito
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Cart cart;

    // Métodos de pago guardados por el usuario
    @OneToMany(mappedBy = "usuario")
    private List<MetodoPago> pagos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<MetodoPago> getPagos() {
        return pagos;
    }

    public void setPagos(List<MetodoPago> pagos) {
        this.pagos = pagos;
    }
}