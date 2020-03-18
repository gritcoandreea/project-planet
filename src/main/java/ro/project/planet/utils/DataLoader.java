package ro.project.planet.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.project.planet.dto.PlanetCreateDto;
import ro.project.planet.exception.ServiceException;
import ro.project.planet.service.PlanetService;

import javax.validation.Valid;

/**
 * Utility class to add data in H2 database.
 */
@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    private PlanetService planetService;

    @Override
    public void run(String... args) throws ServiceException {
        log.info("Loading data starting...");
        createPlanets();
        log.info("Data persisted successfully in H2 db.");
    }

    private void createPlanets() throws ServiceException {
        planetService.savePlanet(PlanetCreateDto.builder().name("Mercur").build());
        planetService.savePlanet(PlanetCreateDto.builder().name("Venus").build());
    }


    @Autowired
    public void setPlanetService(PlanetService planetService) {
        this.planetService = planetService;
    }
}
