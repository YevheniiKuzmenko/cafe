package ua.meta.kuzmenko.yevhenii.cafe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.meta.kuzmenko.yevhenii.cafe.models.OrderCafe;
import ua.meta.kuzmenko.yevhenii.cafe.models.Product;

import java.util.List;

public interface OrderCafeRepository extends JpaRepository<OrderCafe, Long> {

    @Query("SELECT u from OrderCafe u where u.checkIsClosed=:checkIsClosed")
    List<OrderCafe> findOrderCafeByCheckIsClosed(@Param("checkIsClosed") Boolean checkIsClosed);

    @Query("SELECT u from OrderCafe u where u.product=:product")
    List<OrderCafe> findOrderCafeByProduct(@Param("product") Product product);
}
