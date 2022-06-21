package ir.dotin.cardprintrequest.models;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SimpleClass implements ApplicationContextProvider {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

    @Override
    public String toString() {
        return "SimpleClass{" +
                "applicationContext=" + applicationContext +
                '}';
    }
}
