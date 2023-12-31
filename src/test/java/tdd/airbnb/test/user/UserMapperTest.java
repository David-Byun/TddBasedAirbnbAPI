package tdd.airbnb.test.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tdd.airbnb.user.User;
import tdd.airbnb.user.UserMapper;
import tdd.airbnb.user.dto.UserRegisterRequestDto;
import tdd.airbnb.user.dto.UserSignRequestDto;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserMapperTest {

    private final String email ="cc@naver.com";

    @Autowired
    private UserMapper userMapper;

    @Spy
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void UserRepository가NULL아님() {
        Assertions.assertThat(userMapper).isNotNull();
    }

    @Test
    void 회원가입테스트() {
        //given
        User user = User.builder()
                .email("cc@naver.com")
                .nickname("cc")
                .userId(1)
                .username("cc")
                .hostPermission(1)
                .status("Y")
                .privacyAgreement(1)
                .marketingAgreement(1)
                .oauthProvider("NAVER")
                .birth("930203")
                .password("12345")
                .build();

        //when
        userMapper.save(user);
        UserRegisterRequestDto savedUser = userMapper.findUserByEmail(user.getEmail()).get();

        //then
        assertThat(savedUser.getNickname()).isEqualTo("cc");
        assertThat(savedUser.getEmail()).isEqualTo(email);
    }

    @Test
    void 회원중복가입테스트() {

        //given
        User user = User.builder()
                .email("cc@naver.com")
                .nickname("cc")
                .userId(1)
                .username("cc")
                .hostPermission(1)
                .status("Y")
                .privacyAgreement(1)
                .marketingAgreement(1)
                .oauthProvider("NAVER")
                .birth("930203")
                .password("12345")
                .build();

        User user1 = User.builder()
                .email("bb@naver.com")
                .nickname("cc")
                .userId(1)
                .username("cc")
                .hostPermission(1)
                .status("Y")
                .privacyAgreement(1)
                .marketingAgreement(1)
                .oauthProvider("NAVER")
                .birth("930203")
                .password("12345")
                .build();
        //when
        userMapper.save(user1);
        userMapper.save(testUser());
        userMapper.save(user);
        int check = userMapper.checkEmail(email);
        int checkDiffUser = userMapper.checkEmail("bb@naver.com");

        //then
        assertThat(check).isEqualTo(2);
        assertThat(checkDiffUser).isEqualTo(1);
    }

    private User testUser() {
        return User.builder()
                .email("cc@naver.com")
                .nickname("cc")
                .userId(1)
                .username("cc")
                .hostPermission(1)
                .status("Y")
                .privacyAgreement(1)
                .marketingAgreement(1)
                .oauthProvider("NAVER")
                .birth("930203")
                .password("12345")
                .build();
    }

    private UserSignRequestDto loginUser() {
        return UserSignRequestDto.builder()
                .email("cc@naver.com")
                .password("12345")
                .build();
    }

}
