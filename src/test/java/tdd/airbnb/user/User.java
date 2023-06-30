package tdd.airbnb.user;

import lombok.Builder;
import lombok.Getter;
import tdd.airbnb.BaseTimeEntity;

import javax.validation.constraints.NotNull;

@Getter
public class User extends BaseTimeEntity {

    @NotNull
    private long userId;

    @NotNull
    private String username;
    @NotNull
    private String nickname;
    @NotNull
    private String birth;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String status;
    @NotNull
    private int privacyAgreement;
    @NotNull
    private int marketingAgreement;
    @NotNull
    private int hostPermission;
    @NotNull
    private String oauthProvider;

    @Builder
    public User(long userId, String username, String nickname, String birth, String email, String password, String status, int privacyAgreement, int marketingAgreement, int hostPermission, String oauthProvider) {
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
        this.birth = birth;
        this.email = email;
        this.password = password;
        this.status = status;
        this.privacyAgreement = privacyAgreement;
        this.marketingAgreement = marketingAgreement;
        this.hostPermission = hostPermission;
        this.oauthProvider = oauthProvider;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }
}
