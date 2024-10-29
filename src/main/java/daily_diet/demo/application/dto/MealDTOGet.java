package daily_diet.demo.application.dto;

import daily_diet.demo.domain.entities.Meal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealDTOGet {
    public MealDTOGet(Meal meal) {
        meal.setId(meal.getId());
        meal.setName(meal.getName());
        meal.setDescription(meal.getDescription());
        meal.setIsHealthy(meal.getIsHealthy());
        meal.setCreatedAt(meal.getCreatedAt());
        meal.setDate(meal.getDate());
        meal.setId(meal.getId());
    }
    private UUID id;
    private String name;
    private String description;
    private Boolean isHealthy;
    private LocalDateTime createdAt;
    private LocalDateTime date;
    private UUID userId;
}
