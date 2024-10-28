package daily_diet.demo.interfaces.controllers.http;

import daily_diet.demo.application.dto.MealDTO;
import daily_diet.demo.application.dto.MealDTOGet;
import daily_diet.demo.application.services.MealService;
import daily_diet.demo.domain.entities.Meal;
import daily_diet.demo.infra.adapters.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/meal")
public class MealController {

    @Autowired
    private MealService mealService;

    @GetMapping("")
    public ResponseEntity getAll() {
        Stream<MealDTOGet> meals = mealService.getAllMeals();
        return ResponseEntity.status(HttpStatus.OK).body(meals);
    }

    @PostMapping("")
    public ResponseEntity create(@RequestBody MealDTO meal) {
        mealService.createMeal(meal);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
