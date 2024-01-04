package com.example.demo.controllers;


import com.example.demo.entities.Application;
import com.example.demo.entities.MyUser;
import com.example.demo.services.AppService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1/apps")
@AllArgsConstructor
public class AppController {
    private AppService appService;


    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome";
    }

    @GetMapping("/all-app")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Application> applicationList(){
        return appService.applicationList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Application applicationById(@PathVariable int id){
       return appService.applicationById(id);
    }

    @PostMapping("/new-user")
    public String addUser(@RequestBody MyUser myUser){
        appService.addUser(myUser);
        return "Saved Successfully";
        }
}
