package com.example.authsystem.service;

import com.example.authsystem.exceptions.InvalidIdException;
import com.example.authsystem.exceptions.UserIsAdminException;
import com.example.authsystem.model.MyUser;
import com.example.authsystem.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    public List<MyUser> getUsers() {
        return authRepository.findAll();
    }

    public void addUser(MyUser myUser) throws IllegalAccessException {
        authRepository.save(myUser);
    }

    public Integer getUser(Integer id) {
        Optional<MyUser> myUser=authRepository.findById(id);

        if(myUser.isEmpty()){
            return -1;
        }

        return 1;
    }

    public Integer checkUser(Integer id) {
        Optional<MyUser> myUser=authRepository.findById(id);

        if(myUser.isEmpty()){
            return -1;
        }

        return 1;
    }

    public void checkUser2(Integer id) {
        MyUser myUser=authRepository.findById(id)
                .orElseThrow(
                        ()->new InvalidIdException("Invalid id"));

        if(myUser.getRole().equals("ADMIN")){
            throw new UserIsAdminException("User is admin !");
        }
    }
}
