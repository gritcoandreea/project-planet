package ro.project.planet.service;

import com.sun.jdi.event.ExceptionEvent;
import lombok.extern.slf4j.Slf4j;
import org.h2.table.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.project.planet.domain.Planet;
import ro.project.planet.exception.ServiceException;
import ro.project.planet.repository.PlanetRepository;

import java.util.List;

@Service
@Slf4j
public class PlanetService {


    private PlanetRepository planetRepository;

    /**
     * Returns all planets or empty list.
     *
     * @return List<Planet>
     * @throws ServiceException
     */
    public List<Planet> getPlanets() throws ServiceException {
        try {
            return planetRepository.findAll();
        } catch (Exception exception) {
            throw new ServiceException("Error when retrieving planets.", exception);
        }

    }

    public Planet savePlanet(Planet planet) throws ServiceException {
        try {
            return planetRepository.save(planet);
        } catch (RuntimeException exception) {
            throw new ServiceException("Error when saving planet  " + planet.toString(), exception);
        }
    }


    @Autowired
    public void setPlanetRepository(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }
}
