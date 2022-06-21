package ir.dotin.cardprintrequest.aspect;

import ir.dotin.cardprintrequest.models.ActivityLog;
import ir.dotin.cardprintrequest.models.PrintRequest;
import ir.dotin.cardprintrequest.repository.ActivityLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LoggingActivity {

    @Autowired
    ActivityLogRepository activityLogRepository;

    @Autowired
    ActivityLog activityLog;

    @Autowired
    PrintRequest printRequest;

    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void beanPointcut() {

    }


    @Pointcut("within(ir.dotin.cardprintrequest..*)" +
            " || within(ir.dotin.cardprintrequest.service..*)" +
            " || within(ir.dotin.cardprintrequest.controllers..*)")
    public void packagePointcut() {
    }

    @Around("packagePointcut() && beanPointcut() && @annotation(ir.dotin.cardprintrequest.aspect.SaveActivity)")
    public Object saveActivity(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Map<String, Object> arguments;
            arguments = argsToObject(joinPoint.getArgs());
            Object result = joinPoint.proceed();
            saveActivity(arguments, joinPoint);
            return result;
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    private Map<String, Object> argsToObject(Object[] args) {
        Map<String, Object> arguments = new HashMap<>();
        for (Object arg : args) {
            if (arg instanceof PrintRequest) {
                PrintRequest printRequest = (PrintRequest) arg;
                arguments.put("PrintRequest", printRequest);
            }
        }
        return arguments;
    }

    private void saveActivity(Map<String, Object> argsObject, JoinPoint joinPoint) {
        ActivityLog activity= new ActivityLog();
        activity.setOperationName(joinPoint.getSignature().getName());
        if (argsObject.containsKey("PrintRequest")) {
            PrintRequest cardPrintRequestDTO = (PrintRequest) argsObject.get("PrintRequest");
            activity.setPanNumber(cardPrintRequestDTO.getCardPan());
            activity.setUserPersonnelCode(cardPrintRequestDTO.getPersonnelCode());
        }
        activity.setApplicationType(activityLog.getApplicationType());
        activity.setDate(new Date().toString());
        activityLogRepository.save(activity);
    }
}
