package com.spring.backend.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.backend.Entity.userEntity;
import com.spring.backend.Exception.UserException;
import com.spring.backend.Repo.userRepo;
import com.spring.backend.interfaces.UserServiceInterface;

@Service
public class userService implements UserServiceInterface{

    @Autowired private userRepo userRepo;

    private userEntity userEntitys;

    public static Long userId;

    int OTP;

    @Autowired private JavaMailSender javaMailSender;

    @Autowired PasswordEncoder passwordEncoder;

    @Override
    public userEntity UserRegisterService(userEntity ue) {
        userEntity email = userRepo.findByEmail(ue.getEmail());
        userEntity name = userRepo.findByName(ue.getName());
        String pass = passwordEncoder.encode(ue.getPass());
        if (email != null) {
            throw new UserException("this email id Already Registred");
        }else if(name != null){
            throw new UserException("this user name already taken by another user");
        }else if(ue.getName().length() <= 3){
            throw new UserException("user name length should be more than 3 char");
        }else if(ue.getPass().length() < 7){
            throw new UserException("user password length should be more than 7 char");
        }else if(email == null && name == null){
            userEntitys = new userEntity(null, ue.getEmail(), ue.getName(), pass, false, null);
            return ue;
        }
        return null;
    }

    @Override
    public userEntity UserLoginService(userEntity ue) {
        userEntity email = userRepo.findByEmail(ue.getEmail());
        if (email != null && passwordEncoder.matches(ue.getPass(), email.getPass())) {
            userId = email.getUserid();
            System.out.println("true");
            return ue;
        }
        throw new UserException("user email id or password is incorrect");
    }

    @Override
    public List<userEntity> listUsers() {
        List<userEntity> listUsers = userRepo.listUsers();
        return listUsers.stream().filter((users) -> !users.getUserid().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public int OTPGenerate() {
        return (int) ( 99999 + Math.random() * 999999);
    }

    @Override
    public void SendMail(userEntity userEntity) {
        OTP = OTPGenerate();
        int temp = OTP;
        int last = 0;
        while (temp != 0) {
            int rem = temp % 10;
            last = rem;
            temp /= 10;
        }
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(userEntity.getEmail());
        simpleMailMessage.setSubject("Your OTP Code is : "+ last + "XXXXX");
        simpleMailMessage.setText("Dear user [" + userEntity.getName() + "] \n" + "Your OTP Code is ** " + OTP + " ** \n" + "If you did not request this message please ignore");
        javaMailSender.send(simpleMailMessage);
        System.out.println("Mail send success . . .");
    }

    @Override
    public userEntity VerifyUser(int Code) {
        if (OTP == Code) {
            userEntitys.setIscheck(true);
            userEntitys.setCreatedate(new Date());
            userId = userEntitys.getUserid();
            return userRepo.save(userEntitys);
        }
        throw new UserException("Invalid OTP Code Entered");
    }
    
}
