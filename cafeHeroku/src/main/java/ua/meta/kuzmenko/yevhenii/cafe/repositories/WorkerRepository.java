package ua.meta.kuzmenko.yevhenii.cafe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.meta.kuzmenko.yevhenii.cafe.models.OrderCafe;
import ua.meta.kuzmenko.yevhenii.cafe.models.Worker;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    @Query("SELECT u from Worker u where u.name=:name")
    Worker findByName(@Param("name") String name);

}
