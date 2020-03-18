package ro.project.planet.dto.converter;

import org.springframework.stereotype.Component;
import ro.project.planet.domain.Planet;
import ro.project.planet.dto.PlanetDto;
import ro.project.planet.exception.DtoException;

@Component
class PlanetDtoConverter implements DtoConverter<Planet, PlanetDto> {

    @Override
    public PlanetDto convertToDto(Planet pojo) throws DtoException {
        if (pojo != null) {
            PlanetDto planetDto = new PlanetDto();
            planetDto.setId(pojo.getId());
            planetDto.setName(pojo.getName());
            planetDto.setPlanetStatus(pojo.getStatus());
            return planetDto;
        } else {
            throw new DtoException("Team object is null!");
        }

    }
}