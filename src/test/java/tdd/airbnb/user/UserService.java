package tdd.airbnb.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tdd.airbnb.exception.UserException;
import tdd.airbnb.user.dto.UserRegisterRequestDto;
import tdd.airbnb.user.dto.UserRegisterResponseDto;
import tdd.airbnb.user.dto.UserSignRequestDto;
import tdd.airbnb.user.dto.UserSignResponseDto;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {


//    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserRegisterResponseDto register(UserRegisterRequestDto userRegisterRequestDto) {
        int check = userMapper.checkEmail(userRegisterRequestDto.getEmail());
        if (check >= 1) {
            throw new UserException("이미 존재하는 회원입니다");
        }

        User user = userRegisterRequestDto.toEntity();
        // 비밀번호 암호화 추가
        userMapper.save(user);
        return UserRegisterResponseDto.builder()
                .id(user.getUserId())
                .build();
    }

    public UserSignResponseDto login(UserSignRequestDto userSignRequestDto) {
        User foundUser = userMapper.findUserByEmail(userSignRequestDto.getEmail()).orElseThrow(() -> new UserException("존재하지 않는 회원입니다")).toEntity();
//
//        if (!passwordEncoder.matches(userSignRequestDto.getPassword(), foundUser.getPassword())) {
//            throw new UserException("패스워드가 틀려요");
//        }

        return UserSignResponseDto.builder()
                .email(foundUser.getEmail())
                .build();
    }
}
