package pcy.study.storeadmin.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping(path = {"/", "/main"})
    public String index() {
        return "main";
    }
}
