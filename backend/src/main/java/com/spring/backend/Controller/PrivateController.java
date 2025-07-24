package com.spring.backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.backend.Entity.messageEntity;
import com.spring.backend.Entity.userEntity;
import com.spring.backend.Service.messageService;
import com.spring.backend.Service.userService;

@RestController
@RequestMapping("/private")
public class PrivateController {
    
    @Autowired private userService userService;

    @Autowired private messageService mService;

    @GetMapping("/users")
    public List<userEntity> ListUsers(){
        return userService.listUsers();
    }

    @PostMapping("/send")
    public ResponseEntity<String> sneMessage(@RequestBody messageEntity message){
        try {
            messageEntity sned = mService.snedMessage(message);
            if (sned != null) {
                return ResponseEntity.ok().body("message send");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error : "+e.getMessage());
        }
        return ResponseEntity.badRequest().body("failed");
    }

    @GetMapping("/list")
    public List<messageEntity> listMessgae(@RequestParam Long sid,@RequestParam Long rid){
        return mService.listMessage(sid, rid);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long sid,@RequestParam Long rid,@RequestParam String msg){
        mService.DeleteMessage(sid, rid, msg);
        return ResponseEntity.ok().body("Message Deleted");
    }
}
