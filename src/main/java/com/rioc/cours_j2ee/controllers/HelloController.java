package com.rioc.cours_j2ee.controllers;

import com.rioc.cours_j2ee.services.hello.IHelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final IHelloService service;

    public HelloController(IHelloService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String getHello() {
        return "hello";
    }

    @GetMapping("/hello/name")
    public String getHelloForName(@RequestParam("name") String name) {
        return service.getHelloForName(name);
    }
}
