package daily_diet.demo.application.services.erros;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsError extends ResponseStatusException {
    public UserAlreadyExistsError() {
        super(HttpStatus.CONFLICT, "Usuário já existe");
    }
}