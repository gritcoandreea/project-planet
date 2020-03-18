package ro.project.planet.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @NotBlank(message = "Name must not be null!")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PlanetStatus status;

    @Column(name = "teamId")
    private long teamId;
}
