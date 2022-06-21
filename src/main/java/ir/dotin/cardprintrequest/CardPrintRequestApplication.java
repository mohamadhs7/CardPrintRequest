package ir.dotin.cardprintrequest;

import ir.dotin.cardprintrequest.models.PrintRequest;
import ir.dotin.cardprintrequest.models.PrintRequestId;
import ir.dotin.cardprintrequest.models.SimpleClass;
import ir.dotin.cardprintrequest.repository.CustomPrintRequestRepository;
import ir.dotin.cardprintrequest.repository.CustomPrintRequestRepositoryImpl;
import ir.dotin.cardprintrequest.repository.PrintRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.List;

@SpringBootApplication
@EnableWebMvc
@ServletComponentScan
public class CardPrintRequestApplication implements WebMvcConfigurer {

    @Autowired
    PrintRequestRepository prRepo;

    @Autowired
    CustomPrintRequestRepository cpr;


    public static void main(String[] args) {
        SpringApplication.run(CardPrintRequestApplication.class, args);
//        ApplicationContext ctx = SpringApplication.run(CardPrintRequestApplication.class, args);
//        SimpleClass bean = ctx.getBean(SimpleClass.class);
//        System.out.println(bean.toString());
    }

    @Autowired
    WebApplicationContext webApplicationContext;

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(webApplicationContext);
        templateResolver.setOrder(9);
        templateResolver.setPrefix("/WEB-INF/view/");
        templateResolver.setSuffix("");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine springTemplateEngine= new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(thymeleafTemplateResolver());
        springTemplateEngine.setEnableSpringELCompiler(true);
        return springTemplateEngine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(){
        final ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setViewNames(new String[] {"*.html"});
        viewResolver.setExcludedViewNames(new String[] {"*.jsp"});
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

    @Bean
    public InternalResourceViewResolver jspViewResolver(){
        final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setOrder(10);
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix("");
        viewResolver.setViewNames("*.jsp");
        return viewResolver;
    }

    @Bean
    CommandLineRunner runner() {

        return args -> {

            PrintRequestId pri1 = new PrintRequestId("102","10.20.12.35");
            PrintRequestId pri2 = new PrintRequestId("112","10.20.12.36");
            PrintRequestId pri3 = new PrintRequestId("125","10.20.12.37");
            PrintRequestId pri4 = new PrintRequestId("137","0.0.0.0");
//
            PrintRequest p1 = new PrintRequest(pri1,"0023060883","6395991145417077","11-12-2022");
            PrintRequest p2 = new PrintRequest(pri2,"1532545215","6104337979658132","12-12-2022");
            PrintRequest p3 = new PrintRequest(pri3,"0023060883","6395991175210806","13-12-2022");
            PrintRequest p4 = new PrintRequest(pri4,"1371372944","6395991174741872","14-12-2022");

            prRepo.save(p1);
            prRepo.save(p2);
            prRepo.save(p3);
            prRepo.save(p4);

//            Pageable p = PageRequest.of(0,10);
//            List<PrintRequest> p1 = prRepo.getByCardPan("6395991145417077",p);
//            for(PrintRequest pr:p1) {
//                pr.setPersonnelCode("1532545215");
//                prRepo.save(pr);
//            }



//            PrintRequest p1 = prRepo.getByCardPan("6395991145417077");
//            prRepo.delete(p1);

//           List<PrintRequest> printRequests = prRepo.getPrintRequests();
//           for(PrintRequest p : printRequests)
//               System.out.println(p.toString());

//            List<PrintRequest> printRequests = prRepo.getByPersonnelCode("0023060883");
//            for(PrintRequest p : printRequests)
//                System.out.println(p.toString());

            //prRepo.updateIpAdressToUnusedMode();

//            List<String> strings = cpr.getIpAddressesByBranchCode("11");
//            System.out.println(strings);

        };
    }
}
