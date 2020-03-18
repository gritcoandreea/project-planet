package ro.project.planet.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PlanetCreateDto {

    @NotBlank(message = "Name must not be null!")
    private String name;
}
