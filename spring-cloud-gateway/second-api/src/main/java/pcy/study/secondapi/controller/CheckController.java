package pcy.study.secondapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second")
public class CheckController {

    @GetMapping("/public/check")
    public String publicCheck() {
        return "Success Public Second API";
    }

    @GetMapping("/private/check")
    public String privateCheck() {
        return "Success Private Second API";
    }
}
