package tdd.airbnb.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequestDto {
    private String username;
    private String nickname;
    private String birth;
    private String email;
    private String password;
    private boolean marketingAgreement;


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
