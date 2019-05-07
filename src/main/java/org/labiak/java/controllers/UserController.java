package org.labiak.java.controllers;

import lombok.Data;
import org.labiak.java.entities.User;
import org.labiak.java.services.UserService;
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
    private UserService service;

    @PostMapping("/user")
    public User create(@RequestBody User user) {
        return service.create(user);
    }

    @Data
    static class OkResponse {
        private boolean ok;
        private User user;
    }
}
