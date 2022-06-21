package ir.dotin.cardprintrequest.models;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    @Autowired
    public CustomBeanPostProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Nullable
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextProvider) {
            ((ApplicationContextProvider) bean).setApplicationContext(this.applicationContext);
        }
        return bean;
    }
}

