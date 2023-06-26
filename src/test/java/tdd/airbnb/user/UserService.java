package tdd.airbnb.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tdd.airbnb.exception.UserException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserMapper userMapper;

    public UserRegisterResponseDto register(UserRegisterRequestDto userRegisterRequestDto) {
        int check = userMapper.checkEmail(userRegisterRequestDto.getEmail());
        if (check >= 1) {
            throw new UserException("이미 존재하는 회원입니다");
        }

        User user = userRegisterRequestDto.toEntity();
        userMapper.save(user);
        return UserRegisterResponseDto.builder()
                .id(user.getUserId())
                .build();
    }

    //user 가 Optional 인지 아닌지? :
    public User findUserByEmail(String email) {
    }
}
