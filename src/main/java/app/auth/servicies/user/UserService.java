package app.auth.servicies.user;

import app.auth.entities.user.UserEntity;
import app.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED)
public class UserService {
    private final CurrentUserComponent currentUserComponent;
    private final UserRepository userRepository;
    public UserDetails getCurrentUser() {
        UserDetails userDetails = currentUserComponent.getCurrentUser();

        return getUser(userDetails.getUsername());
    }
    public UserDetails createUser(UserEntity entity) {
        if (userRepository.findByLogin(entity.getLogin()).isPresent()) {
            throw new IllegalArgumentException("user with this login exists");
        }
        
        return userRepository.save(entity);
    }
    public UserDetails getUser(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
