package com.finscope.feed;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    // create hello world controller
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }
}
