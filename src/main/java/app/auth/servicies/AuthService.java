package app.auth.servicies;

import app.auth.dto.jwtresponse.JwtResponse;
import app.auth.dto.request.SignInRequest;
import app.auth.dto.request.SignUpRequest;
import app.auth.mappers.UserMapper;
import app.auth.servicies.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public JwtResponse signup(SignUpRequest request) {
        UserDetails resultUserDetails = userService.createUser(userMapper.signUpDtoToUserEntity(request));
        String token = jwtService.generateToken(resultUserDetails);

        return JwtResponse.builder().token(token).build();
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public JwtResponse signin(SignInRequest request) {
        UserDetails resultUser = userService.getUser(request.getLogin());

        if (!passwordEncoder.matches(request.getPassword(), resultUser.getPassword())) {
            throw new IllegalArgumentException("password is invalid!");
        }

        return JwtResponse.builder().token(jwtService.generateToken(resultUser)).build();
    }
}
