package com.spring.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.backend.Entity.userEntity;
import com.spring.backend.Exception.UserException;
import com.spring.backend.Service.userService;
import com.spring.backend.Token.JwtToken;

@RestController
@RequestMapping("/public")
public class PublicController {
    
    @Autowired private userService userService;

    @Autowired private JwtToken jwtToken;

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
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userEntity.getEmail(), userEntity.getPass());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                String Token = jwtToken.GenerateToken(userEntity.getName());
                return ResponseEntity.ok().body(Token);
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
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(users.getEmail(), users.getPass());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                String Token = jwtToken.GenerateToken(users.getName());
                return ResponseEntity.ok().body(Token);
            }
        } catch (UserException e) {
            return ResponseEntity.badRequest().body("error : "+ e.getMessage());
        }
        return ResponseEntity.ok().body("failed !");
    }
}
