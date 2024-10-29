package daily_diet.demo.application.services;
import daily_diet.demo.application.dto.MealDTO;
import daily_diet.demo.application.dto.MealDTOGet;
import daily_diet.demo.application.services.erros.MealNotFoundError;
import daily_diet.demo.application.services.erros.UserNotFoundError;
import daily_diet.demo.domain.entities.Meal;
import daily_diet.demo.domain.entities.User;
import daily_diet.demo.infra.adapters.repository.MealRepository;
import daily_diet.demo.infra.adapters.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MealService {

    public MealService(MealRepository mealRepository, UserRepository userRepository) {
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;
    private final MealRepository mealRepository;


    public Map getAllMeals() {

        List<Meal> meals = mealRepository.findAll();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Map<String, List<MealDTOGet>> mealsByDate = meals.stream()
                .map(meal -> new MealDTOGet(
                        meal.getId(),
                        meal.getName(),
                        meal.getDescription(),
                        meal.getIsHealthy(),
                        meal.getCreatedAt(),
                        meal.getUser().getId()
                ))
                .collect(Collectors.groupingBy(
                        mealDTO -> mealDTO.getCreatedAt().toLocalDate().format(dateFormatter)
                ));

        return mealsByDate;

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

    public void deleteMeal(UUID id) {
        Optional<Meal> meal = mealRepository.findById(id);

        if(meal.isEmpty()) {
            throw new MealNotFoundError();
        }

        mealRepository.deleteById(id);
    }

    public Map<String, Integer> getBestHealthyMealSequence(UUID userId) {
        List<Meal> userMeals = mealRepository.findByUserId(userId);

        int currentSequence = 0;
        int maxSequence = 0;

        for (Meal meal : userMeals) {
            if (Boolean.TRUE.equals(meal.getIsHealthy())) {
                currentSequence++;
                maxSequence = Math.max(maxSequence, currentSequence);
            } else {
                currentSequence = 0;
            }
        }

        Map<String, Integer> result = new HashMap<>();
        result.put("bestHealthyMealSequence", maxSequence);

        return result;
    }
}
