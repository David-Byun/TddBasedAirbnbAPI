package tdd.airbnb.test.user;

import lombok.extern.slf4j.Slf4j;
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
import tdd.airbnb.user.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * 1. 회원가입 테스트(비밀번호 암호화 포함)
 * 2. 회원로그인 테스트(비밀번호 암호화 포함)
 */
@ExtendWith(MockitoExtension.class)
@Slf4j
class UserServiceTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Spy
    private BCryptPasswordEncoder passwordEncoder;

    private final String email = "cc@naver.com";

    @Test
    void 회원등록시존재하는회원테스트() {

        //given
        doReturn(1).when(userMapper).checkEmail(email);

        //when
        UserException result = assertThrows(UserException.class, () -> {
            userService.register(testUser());
        });

        //when
        assertThat(result.getStatus()).isEqualTo("이미 존재하는 회원입니다");

    }

    @Test
    void 회원가입테스트() {

        // given
        UserRegisterRequestDto userRequestDto = testUser();
        User userEntity = userRequestDto.toEntity();

        // Stubbing
        doNothing().when(userMapper).save(any(User.class));

        // when
        UserRegisterResponseDto responseDto = userService.register(userRequestDto);

        // then
        assertThat(responseDto.getId()).isNotNull();

    }

    @Test
    void 유저확인테스트() {

        //given
        when(userService.findUserByEmail(email)).thenReturn(UserRegisterRequestDto);
    }

    private UserRegisterRequestDto testUser() {
        return UserRegisterRequestDto.builder()
                .email("cc@naver.com")
                .nickname("cc")
                .username("cc")
                .marketingAgreement(1)
                .birth("930203")
                .password("12345")
                .build();
    }
}


























