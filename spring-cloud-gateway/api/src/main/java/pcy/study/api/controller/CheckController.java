package pcy.study.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first")
public class CheckController {

    @GetMapping("/public/check")
    public String publicCheck() {
        return "Success Public First API";
    }

    @GetMapping("/private/check")
    public String privateCheck() {
        return "Success Private First API";
    }
}
