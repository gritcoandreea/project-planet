package ro.project.planet.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
//    @Bean(initMethod = "start", destroyMethod = "stop")
//    public Server inMemoryH2DatabaseServer() throws SQLException {
//        String[] args = {"-tcp", "-tcpAllowOthers", "-tcpPort", "9090"};
//        return Server.createTcpServer(args);
//    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


}
