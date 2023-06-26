package tdd.airbnb;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tdd.airbnb.user.User;
import tdd.airbnb.user.UserRegisterRequestDto;
import tdd.airbnb.user.UserRegisterResponseDto;
import tdd.airbnb.user.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        User user = User.builder()
                .email("byun@naver.com")
                .nickname("david")
                .userId(1)
                .username("david")
                .hostPermission(true)
                .status("Y")
                .privacyAgreement(true)
                .marketingAgreement(true)
                .oauthProvider("NAVER")
                .birth("930203")
                .password("12345")
                .build();
    }

    @Test
    void UserRepository가NULL아님() {
        Assertions.assertThat(userRepository).isNotNull();
    }

    @Test
    void 회원가입테스트() {

        //given
        

        //when



        //then
    }

}
