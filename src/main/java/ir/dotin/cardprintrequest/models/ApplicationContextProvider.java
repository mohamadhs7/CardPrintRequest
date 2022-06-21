package ir.dotin.cardprintrequest.models;

import org.springframework.context.ApplicationContext;

public interface ApplicationContextProvider {

    ApplicationContext APPLICATION_CONTEXT = null;

    void setApplicationContext(ApplicationContext applicationContext);

    ApplicationContext getApplicationContext();
}
