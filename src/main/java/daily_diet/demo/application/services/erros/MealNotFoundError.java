package daily_diet.demo.application.services.erros;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MealNotFoundError extends ResponseStatusException {
    public MealNotFoundError() {
        super(HttpStatus.NOT_FOUND, "Refeição não encontrada.");
    }
}