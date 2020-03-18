package ro.project.planet.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ro.project.planet.domain.ErrorResponse;
import ro.project.planet.domain.Planet;
import ro.project.planet.dto.PlanetCreateDto;
import ro.project.planet.dto.PlanetDto;
import ro.project.planet.exception.DtoException;
import ro.project.planet.exception.EntityNotFoundException;
import ro.project.planet.exception.ServiceException;
import ro.project.planet.service.PlanetService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class PlanetController {

    private PlanetService planetService;

    @ExceptionHandler({ServiceException.class, EntityNotFoundException.class, DtoException.class})
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        if (exception instanceof DtoException || exception instanceof EntityNotFoundException) {
            log.error(exception.getMessage());
        } else {
            log.error(exception.getMessage(), exception.getCause());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.builder().error(exception.getMessage()).build());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @GetMapping("/planets")
    public List<PlanetDto> planets() throws ServiceException {
        log.info("Entered get planets endpoint.");
        return planetService.getPlanets();
    }


    @PostMapping("/planet")
    public Planet savePlanet(@RequestBody @Valid PlanetCreateDto planet) throws ServiceException {
        log.info("Entered save planet endpoint with payload .");
        return planetService.savePlanet(planet);
    }

    @PutMapping("/planet")
    public Planet updatePlanet(@RequestBody @Valid Planet planet) throws ServiceException, EntityNotFoundException {
        log.info("Entered save planet endpoint with payload .");
        return planetService.updatePlanet(planet);
    }

    @Autowired
    public void setPlanetService(PlanetService planetService) {
        this.planetService = planetService;
    }
}
