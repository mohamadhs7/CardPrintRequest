package ir.dotin.cardprintrequest.configuration;

import ir.dotin.cardprintrequest.models.ActivityLog;
import ir.dotin.cardprintrequest.models.PrintRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationClass {

    @Bean
    public ActivityLog newAcvtivityLog(){
        return new ActivityLog();
    }

    @Bean
    public PrintRequest newPrintRequest(){
        return new PrintRequest();
    }


}
