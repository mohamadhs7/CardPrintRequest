package ir.dotin.cardprintrequest.components;

import ir.dotin.cardprintrequest.models.PrintRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyRepositoryRestConfigurer implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config , CorsRegistry cors) {
        config.exposeIdsFor(PrintRequest.class);
    }
}
