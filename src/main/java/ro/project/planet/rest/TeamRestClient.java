package ro.project.planet.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ro.project.planet.dto.TeamDto;

@Component
public class TeamRestClient implements RestClient<TeamDto> {

    private RestTemplate restTemplate;
    @Value("${team.microservice.url}")
    private String TEAM_MICRO_SERVICE_URL;


    public TeamDto getById(long id) {
        TeamDto result = null;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(TEAM_MICRO_SERVICE_URL).queryParam("id", id);
        ResponseEntity<TeamDto> teamDtoResponseEntity = restTemplate.getForEntity(builder.toUriString(), TeamDto.class);
        if (teamDtoResponseEntity.getStatusCode() == HttpStatus.OK) {
            result = teamDtoResponseEntity.getBody();
        }
        return result;
    }


    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
