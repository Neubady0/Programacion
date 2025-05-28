package com.example.demoelg.repository;

// 2️⃣  Importa la entidad correspondiente
import com.example.demoelg.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
}
