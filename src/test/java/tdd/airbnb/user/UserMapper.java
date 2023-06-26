package tdd.airbnb.user;


import org.apache.ibatis.annotations.Mapper;
import tdd.airbnb.user.User;

import java.util.Optional;

@Mapper
public interface UserMapper {

    void save(User user);

    Optional<User> findUserByEmail(String email);

    int checkEmail(String email);
}
