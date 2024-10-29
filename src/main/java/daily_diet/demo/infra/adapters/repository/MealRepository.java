package daily_diet.demo.infra.adapters.repository;

import daily_diet.demo.domain.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface MealRepository extends JpaRepository<Meal, UUID> {

    List<Meal> findByUserId(UUID userId);
}
