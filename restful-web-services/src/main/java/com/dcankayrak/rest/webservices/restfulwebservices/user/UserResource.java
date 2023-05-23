package com.dcankayrak.rest.webservices.restfulwebservices.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {
    
    private UserDaoService theUserDaoService;
    
    public UserResource(UserDaoService theUserDaoService){
        this.theUserDaoService = theUserDaoService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return this.theUserDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getOneUser(@PathVariable int id){
        return this.theUserDaoService.findOne(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User theUser){
        return this.theUserDaoService.createUser(theUser);
    }
}
