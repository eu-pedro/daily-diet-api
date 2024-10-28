package daily_diet.demo.application.services.erros;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundError extends ResponseStatusException {
    public UserNotFoundError() {
        super(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
    }
}
