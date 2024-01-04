package com.example.demo.services;

import com.example.demo.entities.Application;
import com.example.demo.entities.MyUser;
import com.example.demo.repository.UserRepository;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class AppService {
    private List<Application> applicationList;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void loadAppInDb(){
        Faker faker = new Faker();
        applicationList= IntStream.rangeClosed(1,100)
                .mapToObj(i->Application.builder()
                        .id(i)
                        .name(faker.app().name())
                        .author(faker.app().author())
                        .version(faker.app().version())
                        .build()).toList();
    }

    public List<Application> applicationList(){
        return applicationList;
    }
    public Application applicationById(int id){
        return applicationList.stream().filter(i->i.getId()==id).findFirst()
                .orElse(null);
    }

    public void addUser(MyUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }



}
