package ro.project.planet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.project.planet.domain.Planet;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {

}