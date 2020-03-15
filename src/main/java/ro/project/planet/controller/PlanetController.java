package ro.project.planet.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class PlanetController {

    @GetMapping("/planets")
    public Map<String, String> planets() {
        Map<String, String> resp = new HashMap<>();
        resp.put("response", "Planet microservice works!");
        return resp;
    }
}
