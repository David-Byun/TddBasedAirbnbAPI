package tdd.airbnb.test.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tdd.airbnb.exception.UserException;
import tdd.airbnb.user.User;
import tdd.airbnb.user.UserMapper;
import tdd.airbnb.user.UserService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Spy
    private BCryptPasswordEncoder passwordEncoder;


    private final String email = "david@naver.com";

    @Test
    void 회원미등록조회시NULL() {
        //given
        Optional<User> userByEmail = doReturn(null).when(userMapper).findUserByEmail(email);

        //when
        userService.findUserByEmail(email);

        //when
        assertThat(userByEmail).isEqualTo(null);

    }

    @Test
    void 회원등록() {
        //given
        doReturn(null).when(userMapper).findUserByEmail(email);
    }

}
