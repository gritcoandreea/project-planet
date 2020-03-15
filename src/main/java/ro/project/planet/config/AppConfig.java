package ro.project.planet.config;

import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseServer() throws SQLException {
        String[] args = {"-tcp", "-tcpAllowOthers", "-tcpPort", "9090"};
        return Server.createTcpServer(args);
    }
}
