package ro.project.planet.dto;

import lombok.*;
import ro.project.planet.domain.PlanetStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PlanetDto {

    private long id;
    private String name;
    private PlanetStatus planetStatus;
    private TeamDto team;
}
