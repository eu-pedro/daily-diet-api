package daily_diet.demo.application.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealUpdateDTO {
    private String name;
    private String description;
    private Boolean isHealthy;
    private UUID userId;
}
