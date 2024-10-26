package daily_diet.demo.infra.adapters.repository;

import daily_diet.demo.domain.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MealRepository extends JpaRepository<Meal, UUID> {
}
