package com.spring.backend.interfaces;

import java.util.List;

import com.spring.backend.Entity.userEntity;

public interface UserServiceInterface {

    userEntity UserRegisterService(userEntity userEntity);
    
    userEntity UserLoginService(userEntity userEntity);

    List<userEntity> listUsers();

    int OTPGenerate();

    void SendMail(userEntity userEntity);

    userEntity VerifyUser(int Code);
}
