package ro.project.planet.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.project.planet.domain.Planet;
import ro.project.planet.domain.PlanetStatus;
import ro.project.planet.dto.converter.DtoConverter;
import ro.project.planet.dto.PlanetCreateDto;
import ro.project.planet.dto.PlanetDto;
import ro.project.planet.dto.TeamDto;
import ro.project.planet.exception.DtoException;
import ro.project.planet.exception.EntityNotFoundException;
import ro.project.planet.exception.ServiceException;
import ro.project.planet.repository.PlanetRepository;
import ro.project.planet.rest.TeamRestClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PlanetService {


    private PlanetRepository planetRepository;
    private TeamRestClient teamRestClient;
    private DtoConverter<Planet, PlanetDto> dtoConverter;

    /**
     * Returns all planets or empty list.
     *
     * @return List<Planet>
     * @throws ServiceException
     */
    public List<PlanetDto> getPlanets() throws ServiceException {
        try {
            List<Planet> planets = planetRepository.findAll();
            return planets.stream().map(planet -> {
                try {
                    PlanetDto planetDto = dtoConverter.convertToDto(planet);
                    if (planet.getTeamId() != 0) {
                        TeamDto teamDto = teamRestClient.getById(planet.getTeamId());
                        planetDto.setTeam(teamDto);
                    }
                    return planetDto;
                } catch (DtoException e) {
                    return null;
                }
            }).collect(Collectors.toList());
        } catch (Exception exception) {
            throw new ServiceException("Error when retrieving planets.", exception);
        }

    }

    public Planet savePlanet(PlanetCreateDto planetDto) throws ServiceException {
        try {
            Planet planet = new Planet();
            planet.setName(planetDto.getName());
            planet.setStatus(PlanetStatus.TODO);
            return planetRepository.save(planet);
        } catch (RuntimeException exception) {
            throw new ServiceException("Error when saving planet with name " + planetDto.getName(), exception);
        }
    }

    public Planet updatePlanet(Planet planet) throws ServiceException, EntityNotFoundException {
        try {
            Planet dbPlanet = planetRepository.findById(planet.getId()).orElse(null);
            if (dbPlanet != null) {
                long teamId = planet.getTeamId();
                if (teamId != 0 && teamRestClient.getById(teamId) == null) {
                    throw new EntityNotFoundException("Team with id " + teamId + " not found!");
                }
                return planetRepository.save(planet);
            } else {
                throw new EntityNotFoundException("Planet with id " + planet.getId() + " not found!");
            }
        } catch (RuntimeException exception) {
            throw new ServiceException("Error when updating planet with id " + planet.getId(), exception);
        }
    }


    @Autowired
    public void setPlanetRepository(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @Autowired
    public void setTeamRestClient(TeamRestClient teamRestClient) {
        this.teamRestClient = teamRestClient;
    }

    @Autowired
    public void setDtoConverter(DtoConverter<Planet, PlanetDto> dtoConverter) {
        this.dtoConverter = dtoConverter;
    }
}
