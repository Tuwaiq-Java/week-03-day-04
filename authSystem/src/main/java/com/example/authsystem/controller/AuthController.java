package com.example.authsystem.controller;

import com.example.authsystem.DTO.API;
import com.example.authsystem.model.MyUser;
import com.example.authsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/users")
    public ResponseEntity<List<MyUser>> getUsers(){
        List<MyUser> users=authService.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<String> checkUserIsValid(@PathVariable Integer id){
        Integer userStatus=authService.checkUser(id);
        switch (userStatus){
            case -1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid id");
            case 1:
                return ResponseEntity.status(HttpStatus.OK).body("Valid user");
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SERVER ERROR");

        }
    }

    @GetMapping("/user2/{id}")
    public ResponseEntity<API> checkUserIsValid2(@PathVariable Integer id){
        authService.checkUser2(id);
        return ResponseEntity.status(HttpStatus.OK).body(new API("Valid user",200));
    }

    @PostMapping("/register")
    public ResponseEntity<API> addUser(@RequestBody @Valid MyUser myUser) throws IllegalAccessException{
        authService.addUser(myUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new API("New user added !",201));
    }
}
