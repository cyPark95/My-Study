package pcy.study.elk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/sleep")
public class SleepController {

    @GetMapping
    public void logSleep() throws InterruptedException {
        Thread.sleep(1000);
        log.info("Sleep Log");
    }
}
