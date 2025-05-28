package com.example.demoelg.repository;
// 2️⃣  Importa la entidad correspondiente
import com.example.demoelg.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
}
