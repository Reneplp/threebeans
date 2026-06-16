package de.rene.threebeans.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String testApp() {
        return "Test, ob alles funktioniert.";
    }
}