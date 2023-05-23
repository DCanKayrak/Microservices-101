package com.dcankayrak.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class UserDaoService {
    // JPA - HIBERNATE


    private static List<User> users = new ArrayList<>();

    private static int userCounter = 0;
    static{
        users.add(new User(++userCounter, "Danyal",LocalDate.now().minusYears(20)));
        users.add(new User(++userCounter, "Can",LocalDate.now().minusYears(35)));
        users.add(new User(++userCounter, "Ahmet",LocalDate.now().minusYears(70)));
    }

    public List<User> findAll(){
        return users;
    }

    public User findOne(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().get();
    }

    public User createUser(User theUser) {
        theUser.setId(++userCounter);
        users.add(theUser);
        return theUser;
    }
}
