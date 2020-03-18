package ro.project.planet.dto.converter;

import ro.project.planet.exception.DtoException;

public interface DtoConverter<P, D> {
    D convertToDto(P pojo) throws DtoException;
}
