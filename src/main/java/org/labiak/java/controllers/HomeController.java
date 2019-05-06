package org.labiak.java.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HomeController {
    @Autowired
    private ObjectMapper mapper;

    @GetMapping(path = "")
    public ObjectNode main() {
        LocalDateTime d = LocalDateTime.now();
        ObjectNode object = mapper.createObjectNode();
        object.put("ok", true);
        object.put("time", DateTimeFormatter.ISO_DATE_TIME.format(d));
        return object;
    }

//    @ExceptionHandler(Exception.class)
//    public String handleError(HttpServletRequest req, Exception ex) {
//        return "Error";
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String notFound(HttpServletRequest req) {
//        return "Not found";
//    }
}
