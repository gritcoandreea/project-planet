package ro.project.planet.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.project.planet.domain.ErrorResponse;
import ro.project.planet.domain.Planet;
import ro.project.planet.exception.ServiceException;
import ro.project.planet.service.PlanetService;

import java.util.List;

@RestController
@Slf4j
public class PlanetController {

    private PlanetService planetService;

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<ErrorResponse> handleException(ServiceException exception) {
        log.error(exception.getMessage(), exception.getCause());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(exception.getMessage()));
    }

    @GetMapping("/planets")
    public List<Planet> planets() throws ServiceException {
        log.info("Entered teams endpoint.");
        return planetService.getPlanets();
    }

    @Autowired
    public void setPlanetService(PlanetService planetService) {
        this.planetService = planetService;
    }
}
