package ua.meta.kuzmenko.yevhenii.cafe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.meta.kuzmenko.yevhenii.cafe.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
