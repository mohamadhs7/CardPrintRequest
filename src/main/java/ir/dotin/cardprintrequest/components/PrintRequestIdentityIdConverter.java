package ir.dotin.cardprintrequest.components;

import ir.dotin.cardprintrequest.models.PrintRequest;
import ir.dotin.cardprintrequest.models.PrintRequestId;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class PrintRequestIdentityIdConverter implements BackendIdConverter {

    @Override
    public Serializable fromRequestId(String id, Class<?> aClass) {
        if(id==null) {
            return null;
        }
        String[] parts = id.split("_");
        return new PrintRequestId(parts[0], parts[1]);
    }

    @Override
    public String toRequestId(Serializable source, Class<?> aClass) {
        PrintRequestId id = (PrintRequestId) source;
        return String.format("%s_%s", id.getBranchCode(), id.getIpAddress());
    }

    @Override
    public boolean supports(Class<?> type) {
        return PrintRequest.class.equals(type);
    }

}
