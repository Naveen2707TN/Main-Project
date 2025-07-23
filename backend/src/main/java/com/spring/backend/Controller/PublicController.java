package com.spring.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.backend.Entity.userEntity;
import com.spring.backend.Exception.UserException;
import com.spring.backend.Service.userService;

@RestController
@RequestMapping("/public")
public class PublicController {
    
    @Autowired private userService userService;

    @PostMapping("/reg")
    public ResponseEntity<?> RegisterUser(@RequestBody userEntity userEntity){
        try {
            userEntity users = userService.UserRegisterService(userEntity);
            if (users != null) {
                return ResponseEntity.ok().body("move");
            }
        } catch (UserException e) {
            return ResponseEntity.badRequest().body("error : "+ e.getMessage());
        }
        return ResponseEntity.ok().body("failed !");
    }

    @PostMapping("/log")
    public ResponseEntity<?> LoginUser(@RequestBody userEntity userEntity){
        try {
            userEntity users = userService.UserLoginService(userEntity);
            if (users != null) {
                return ResponseEntity.ok().body("Login Success");
            }
        } catch (UserException e) {
            return ResponseEntity.badRequest().body("error : "+ e.getMessage());
        }
        return ResponseEntity.ok().body("failed !");
    }

    @PostMapping("/verify")
    public ResponseEntity<?> RegisterUser(@RequestParam int Code){
        try {
            userEntity users = userService.VerifyUser(Code);
            if (users != null) {
                return ResponseEntity.ok().body("Register Success ");
            }
        } catch (UserException e) {
            return ResponseEntity.badRequest().body("error : "+ e.getMessage());
        }
        return ResponseEntity.ok().body("failed !");
    }
}
