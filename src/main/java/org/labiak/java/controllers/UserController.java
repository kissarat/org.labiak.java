package org.labiak.java.controllers;

import lombok.Data;
import org.labiak.java.entities.User;
import org.labiak.java.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
    @Autowired
    private UserRepository repository;

    @PostMapping("/user")
    public User create(@RequestBody User user) {
        return repository.save(user);
    }

    @Data
    static class OkResponse {
        private boolean ok;
        private User user;
    }
}
