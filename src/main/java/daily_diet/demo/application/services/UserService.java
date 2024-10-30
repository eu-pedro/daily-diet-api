package daily_diet.demo.application.services;
import daily_diet.demo.application.dto.UserDTO;
import daily_diet.demo.application.dto.UserRegisterDTO;
import daily_diet.demo.application.services.erros.UserAlreadyExistsError;
import daily_diet.demo.application.services.erros.UserNotFoundError;
import daily_diet.demo.domain.entities.User;
import daily_diet.demo.infra.adapters.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

   public void createUser(UserRegisterDTO userDTO) {
      User userAlreadyExists = userRepository.findByUsername(userDTO.username());

       if (userAlreadyExists != null) {
           throw new UserAlreadyExistsError();
       }

       String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());

       User user = new User();
       user.setName(userDTO.name());
       user.setPassword(encryptedPassword);
       user.setUsername(userDTO.username());
       userRepository.save(user);
   }

   public Optional<User> findById(UUID id) {
       Optional<User> user = userRepository.findById(id);
       if(user.isEmpty()) {
           throw new UserNotFoundError();
       }
       return user;
   }


   public List<UserDTO> getAllUsers() {
       List<User> users =  userRepository.findAll();
       return users.stream().map(user -> new UserDTO(
               user.getId(),
               user.getUsername(),
               user.getName()
       )).collect(Collectors.toList());
   }

   public void deleteById (UUID id) {
       Optional<User> user = userRepository.findById(id);

       if(user.isEmpty()) {
           throw new UserNotFoundError();
       }

       userRepository.deleteById(id);
   }
}
