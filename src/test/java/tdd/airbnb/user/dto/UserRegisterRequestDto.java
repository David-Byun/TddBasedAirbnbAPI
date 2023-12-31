package tdd.airbnb.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tdd.airbnb.user.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterRequestDto {
    private String username;
    private String nickname;
    private String birth;
    private String email;
    private String password;
    private int marketingAgreement;


    public User toEntity() {
        return User.builder()
                .username(username)
                .nickname(nickname)
                .birth(birth)
                .email(email)
                .password(password)
                .marketingAgreement(marketingAgreement)
                .build();
    }

}
