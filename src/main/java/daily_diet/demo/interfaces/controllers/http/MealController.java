package daily_diet.demo.interfaces.controllers.http;

import daily_diet.demo.application.dto.MealDTO;
import daily_diet.demo.application.dto.MealDTOGet;
import daily_diet.demo.application.dto.MealDTOUpdate;
import daily_diet.demo.application.services.MealService;
import daily_diet.demo.domain.entities.Meal;
import daily_diet.demo.infra.adapters.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
@RequestMapping("/meal")
public class MealController {

    @Autowired
    private MealService mealService;

    @GetMapping("")
    public ResponseEntity getAll() {
        Map meals = mealService.getAllMeals();
        return ResponseEntity.status(HttpStatus.OK).body(meals);
    }

    @PostMapping("")
    public ResponseEntity create(@RequestBody MealDTO meal) {
        mealService.createMeal(meal);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        mealService.deleteMeal(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/best-helthy-sequence")
    public ResponseEntity getBestHealthyMealSequence(@PathVariable UUID id) {
        Map<String, Integer> bestSequence = mealService.getBestHealthyMealSequence(id);

        return ResponseEntity.status(HttpStatus.OK).body(bestSequence);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody MealDTOUpdate meal) {
        mealService.updateMeal(id, meal);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
