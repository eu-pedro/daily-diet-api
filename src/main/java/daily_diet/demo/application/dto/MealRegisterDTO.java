package daily_diet.demo.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealRegisterDTO {
    private String name;
    private String description;
    private Boolean isHealthy;
    private LocalDateTime date;
    private UUID userId;
}
