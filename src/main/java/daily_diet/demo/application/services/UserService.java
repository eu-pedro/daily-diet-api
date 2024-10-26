package daily_diet.demo.application.services;

import daily_diet.demo.application.dto.UserDTO;
import daily_diet.demo.domain.entities.User;
import daily_diet.demo.infra.adapters.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

   public void createUser(UserDTO userDTO) {
       Optional<User> userAlreadyExists = userRepository.findByUsername(userDTO.getUsername());

       if (userAlreadyExists.isPresent()) {
           throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário já existe");
       }


       User user = new User();
       user.setName(userDTO.getName());
       user.setUsername(userDTO.getUsername());
       user.setPassword(userDTO.getPassword());
       userRepository.save(user);
   }

   public User findById(UUID id) {
       return userRepository.findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
   }
}
