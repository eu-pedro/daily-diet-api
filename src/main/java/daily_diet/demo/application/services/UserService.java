package daily_diet.demo.application.services;

import daily_diet.demo.application.dto.UserDTO;
import daily_diet.demo.application.services.erros.UserAlreadyExistsError;
import daily_diet.demo.application.services.erros.UserNotFoundError;
import daily_diet.demo.domain.entities.User;
import daily_diet.demo.infra.adapters.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
           throw new UserAlreadyExistsError();
       }

       User user = new User();
       user.setName(userDTO.getName());
       user.setUsername(userDTO.getUsername());
       user.setPassword(userDTO.getPassword());
       userRepository.save(user);
   }

   public Optional<User> findById(UUID id) {
       Optional<User> user = userRepository.findById(id);
       if(user.isEmpty()) {
           throw new UserNotFoundError();
       }
       return user;
   }


   public List<User> getAllUsers() {
       return userRepository.findAll();
   }

   public void deleteById (UUID id) {
       Optional<User> user = userRepository.findById(id);

       if(user.isEmpty()) {
           throw new UserNotFoundError();
       }

       userRepository.deleteById(id);
   }
}
