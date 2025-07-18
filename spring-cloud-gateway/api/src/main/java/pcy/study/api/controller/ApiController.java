package pcy.study.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/yml")
    public String ymlRoute() {
        return "Route based on YML configuration file succeeded";
    }

    @GetMapping("/java")
    public String javaRoute() {
        return "Route based on Java code succeeded";
    }

    @PostMapping("/account")
    public String account(@RequestHeader("x-username") String username) {
        return String.format("Received username: %s", username);
    }
}
