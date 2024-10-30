package daily_diet.demo.interfaces.controllers.http;

import daily_diet.demo.application.dto.UserDTO;
import daily_diet.demo.application.dto.UserRegisterDTO;
import daily_diet.demo.application.services.UserService;
import daily_diet.demo.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable UUID id) {
        Optional<User> user = userService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping()
    public ResponseEntity getAll() {
        List<UserDTO> users =  userService.getAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
