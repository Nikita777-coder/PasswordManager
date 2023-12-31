package app.auth.mappers;

import app.auth.dto.request.SignUpRequest;
import app.auth.entities.user.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserMapperTest {
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    @DisplayName("signUpDtoToUserEntity test;" +
            "give SignUpRequest{email=\"mrucj\", login=\"ieudjfvh\", password=\"flvnslvnsc\"};" +
            "SignUpRequest password must be equal to entity password")
    void signUpDtoToUserEntity() {
        SignUpRequest request = SignUpRequest.builder().email("mrucj").login("ieudjfvh").password("flvnslvnsc").build();
        UserEntity entity = userMapper.signUpDtoToUserEntity(request);

        assertTrue(request.getPassword().equals(entity.getPassword()) && entity.getId() == null);
    }
}