package ua.meta.kuzmenko.yevhenii.cafe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.meta.kuzmenko.yevhenii.cafe.models.OrderCafe;
import ua.meta.kuzmenko.yevhenii.cafe.models.Statistics;

public interface StatisticRepository extends JpaRepository<Statistics, Long> {

}
