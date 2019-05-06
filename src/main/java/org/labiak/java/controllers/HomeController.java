package org.labiak.java.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", produces = "application/json")
public class HomeController {

    @GetMapping(value = "")
    public String main() {
        return "Hello";
    }
}
