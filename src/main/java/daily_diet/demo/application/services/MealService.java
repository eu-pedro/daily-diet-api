package daily_diet.demo.application.services;

import daily_diet.demo.application.dto.MealDTO;
import daily_diet.demo.application.dto.MealDTOGet;
import daily_diet.demo.application.services.erros.UserNotFoundError;
import daily_diet.demo.domain.entities.Meal;
import daily_diet.demo.domain.entities.User;
import daily_diet.demo.infra.adapters.repository.MealRepository;
import daily_diet.demo.infra.adapters.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MealService {

    public MealService(MealRepository mealRepository, UserRepository userRepository) {
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;
    private final MealRepository mealRepository;


    public List<MealDTOGet> getAllMeals() {

        List<Meal> meals = mealRepository.findAll();
        List<MealDTOGet> mealDTOGets = new ArrayList<>();

        return mealDTOGets;
    }

    public void createMeal (MealDTO mealDTO) {
        Optional<User> user = userRepository.findById(mealDTO.getUserId());

        if(user.isEmpty()) {
            throw new UserNotFoundError();
        }

        Meal meal = new Meal();
        meal.setName(mealDTO.getName());
        meal.setDescription(mealDTO.getDescription());
        meal.setIsHealthy(mealDTO.getIsHealthy());
        meal.setUser(user.get());
        mealRepository.save(meal);
    }
}
