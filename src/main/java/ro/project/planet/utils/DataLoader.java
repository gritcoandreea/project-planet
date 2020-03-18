package ro.project.planet.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.project.planet.domain.Planet;
import ro.project.planet.domain.PlanetStatus;
import ro.project.planet.exception.ServiceException;
import ro.project.planet.service.PlanetService;

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
        planetService.savePlanet(Planet.builder().name("Mercur").status(PlanetStatus.TODO).build());
        planetService.savePlanet(Planet.builder().name("Venus").status(PlanetStatus.TODO).build());
    }


    @Autowired
    public void setPlanetService(PlanetService planetService) {
        this.planetService = planetService;
    }
}
