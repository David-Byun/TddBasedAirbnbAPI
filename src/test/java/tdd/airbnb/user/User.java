package tdd.airbnb.user;

import lombok.Builder;
import lombok.Getter;
import tdd.airbnb.BaseTimeEntity;

import javax.validation.constraints.NotNull;

@Getter
public class User extends BaseTimeEntity {

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
    private boolean privacyAgreement;
    @NotNull
    private boolean marketingAgreement;
    @NotNull
    private boolean hostPermission;
    @NotNull
    private String oauthProvider;

    @Builder
    public User(long userId, String username, String nickname, String birth, String email, String password, String status, boolean privacyAgreement, boolean marketingAgreement, boolean hostPermission, String oauthProvider) {
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


}
