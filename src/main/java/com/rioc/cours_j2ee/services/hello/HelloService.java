package com.rioc.cours_j2ee.services.hello;

import org.springframework.stereotype.Service;

@Service
public class HelloService implements IHelloService {
    public HelloService() {}

    public String getHelloForName(String name) {
        return "Hello + " + name + " : " + getLength(name);
    }

    private int getLength(String name) {
        return name.length();
    }
}
