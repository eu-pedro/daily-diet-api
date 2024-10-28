package daily_diet.demo.application.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MealDTOGet {
    private String name;
    private String description;
    private Boolean isHealthy;
    private UUID userId;
}
