package com.jee.NTA.crud;

import com.jee.NTA.entities.User;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserCRUD {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private static PasswordEncoder passwordEncoder;

    public static User registerNewUserAccount(String user_name, String user_mail, String user_pass) throws Exception {
//        if (emailExist(accountDto.getEmail())) {
//            throw new EmailExistsException(
//                    "There is an account with that email adress:" + accountDto.getEmail());
//        }
        User user = new User();
        user.setName(user_name);
        user.setMail(user_mail);
        user.setPassword(passwordEncoder.encode(user_pass));

        return user;
    }



}
