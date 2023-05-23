package com.dcankayrak.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
        User theUser = this.theUserDaoService.findOne(id);

        if(theUser == null){
            throw new UserNotFoundException("id : "+id);
        }

        return theUser;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User theUser){

        User createdUser = this.theUserDaoService.createUser(theUser);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdUser.getId())
                        .toUri();
                        
        return ResponseEntity.created(location).build();
    }
}
