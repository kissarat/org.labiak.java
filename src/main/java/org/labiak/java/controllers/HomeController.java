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

import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

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

    @GetMapping(path = "/beans")
    public ArrayList<ClassDescription> beans() {
        String[] names = context.getBeanDefinitionNames();
        ArrayList<ClassDescription> list = new ArrayList<>(names.length);
        for(String name : names) {
            ClassDescription description = new ClassDescription();
            description.setName(name);
            try {
                Class c = Class.forName(name);
                Annotation[] annotations = c.getAnnotations();
                ArrayList<AnnotationDescription> annotationDescriptions = new ArrayList<>(annotations.length);
                for(Annotation annotation : annotations) {
                    AnnotationDescription annotationDescription = new AnnotationDescription();
                            annotationDescription.setName(annotation.annotationType().getName());
                            annotationDescription.setDescription(annotation.toString());
                    annotationDescriptions.add(annotationDescription);
                }
                description.setAnnotations(annotationDescriptions);
            }
            catch (ClassNotFoundException ex) {

            }
            list.add(description);
        }
        return list;
    }

    @Data
    static class AnnotationDescription {
        private String name;
        private String description;
    }

    @Data
    static class ClassDescription {
        private String name;
        private ArrayList<AnnotationDescription> annotations;
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
