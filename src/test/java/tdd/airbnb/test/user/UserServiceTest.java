package tdd.airbnb.test.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tdd.airbnb.exception.UserException;
import tdd.airbnb.user.User;
import tdd.airbnb.user.UserMapper;
import tdd.airbnb.user.UserService;
import tdd.airbnb.user.dto.UserRegisterRequestDto;
import tdd.airbnb.user.dto.UserRegisterResponseDto;
import tdd.airbnb.user.dto.UserSignRequestDto;
import tdd.airbnb.user.dto.UserSignResponseDto;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

        // Stubbing
        doNothing().when(userMapper).save(any(User.class));

        // when
        UserRegisterResponseDto responseDto = userService.register(userRequestDto);

        // then
        assertThat(responseDto.getId()).isNotNull();
    }

    @Test
    void 패스워드불일치시Exception테스트() {

        //given
        when(passwordEncoder.matches(loginUser().getPassword(), testUser().getPassword())).thenReturn(false);
        when(userMapper.findUserByEmail(loginUser().getEmail())).thenReturn(Optional.ofNullable(testUser()));

        //when
        UserException result = assertThrows(UserException.class, () -> {
            userService.login(loginUser());
        });

        //then
        assertThat(result.getStatus()).isEqualTo("패스워드가 틀려요");
    }

    @Test
    void 패스워드일치시통과테스트() {

        testUser().setPassword(passwordEncoder.encode("12345"));

        //given
        when(passwordEncoder.matches(loginUser().getPassword(), testUser().getPassword())).thenReturn(true);

        when(userMapper.findUserByEmail(loginUser().getEmail())).thenReturn(Optional.ofNullable(testUser()));


        //when
        UserSignResponseDto result = userService.login(loginUser());

        //then
        assertThat(result.getEmail()).isEqualTo(loginUser().getEmail());
    }

    @Test
    void 비밀번호암호화테스트() {

        /**
         * Request 입력한 암호와 실제 암호와 비교해서 틀린 것 확인
         */

        // given
        UserRegisterRequestDto userRequestDto = testUser();
        User userEntity = userRequestDto.toEntity();
        String newPassword = passwordEncoder.encode(userRequestDto.getPassword());
        userEntity.setPassword(newPassword);
        log.info("====== 입력 비밀번호 : {} ======= ", userRequestDto.getPassword());
        log.info("====== 암호화 비밀번호 : {} ======= ", userEntity.getPassword());

        //when
        assertThat(userEntity.getPassword()).isNotEqualTo(userRequestDto.getPassword());

    }

    /**
     * 1. 로그인시 이메일 존재 여부 확인 - 이메일이 없을 경우 Exception 처리
     * - 이메일로 확인해서 유저가 있는지 확인하면 되므로 리턴값은 복잡할 필요 없음
     * 2. 회원이 존재한다면 패스워드 일치 여부 확인
     */
    @Test
    void 로그인시이메일없는테스트() {

        //given
        doReturn(Optional.empty()).when(userMapper).findUserByEmail(loginUser().getEmail());

        //when
        UserException result = assertThrows(UserException.class, () -> {
            userService.login(loginUser());
        });

        //then
        assertThat(result.getStatus()).isEqualTo("존재하지 않는 회원입니다");

    }

    private UserSignRequestDto loginUser() {
        return UserSignRequestDto.builder()
                .email("cc@naver.com")
                .password("12345")
                .build();
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


























