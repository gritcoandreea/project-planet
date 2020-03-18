package ro.project.planet.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TeamDto {

    private long id;
    private String name;
    private String captainName;
    private Set<String> robots;
}
