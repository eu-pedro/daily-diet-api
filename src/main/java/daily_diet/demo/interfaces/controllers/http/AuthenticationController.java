package daily_diet.demo.interfaces.controllers.http;

import daily_diet.demo.application.dto.AuthenticationDTO;
import daily_diet.demo.application.dto.LoginResponseDTO;
import daily_diet.demo.application.dto.UserRegisterDTO;
import daily_diet.demo.application.services.UserService;
import daily_diet.demo.domain.entities.User;
import daily_diet.demo.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO user) {
        var hashedPassword = new UsernamePasswordAuthenticationToken(user.username(), user.password());

        var auth = this.authenticationManager.authenticate(hashedPassword);

        var token = tokenService.generate((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRegisterDTO user) {
        System.out.println(user);
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
