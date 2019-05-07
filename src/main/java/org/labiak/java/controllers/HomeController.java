package org.labiak.java.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HomeController {
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ApplicationContext context;

    @GetMapping(path = "/about")
    public ObjectNode main() {
        RestTemplate client = new RestTemplate();
        ResponseEntity<IpResponse> r = client.getForEntity("https://api.ipify.org?format=json", IpResponse.class);
        LocalDateTime d = LocalDateTime.now();
        ObjectNode object = mapper.createObjectNode();
        LocalDateTime startup = LocalDateTime.ofEpochSecond(context.getStartupDate() / 1000, 0, ZoneOffset.ofHours(2));
        object.put("ok", true);
        object.put("time", DateTimeFormatter.ISO_DATE_TIME.format(d));
        object.put("ip", r.getBody().ip);
        object.put("name", context.getApplicationName());
        object.put("displayName", context.getDisplayName());
        object.put("id", context.getId());
        object.put("hasParent", context.getParent() != null);
        object.put("startup", DateTimeFormatter.ISO_DATE_TIME.format(startup));
        return object;
    }

    @Data
    static class IpResponse {
        private String ip;
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
