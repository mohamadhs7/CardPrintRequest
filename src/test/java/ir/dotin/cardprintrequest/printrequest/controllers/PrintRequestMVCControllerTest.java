package ir.dotin.cardprintrequest.printrequest.controllers;

import ir.dotin.cardprintrequest.controllers.PrintRequestController;
import ir.dotin.cardprintrequest.controllers.PrintRequestControllerMVC;
import ir.dotin.cardprintrequest.models.PrintRequest;
import ir.dotin.cardprintrequest.repository.CustomPrintRequestRepository;
import ir.dotin.cardprintrequest.repository.PrintRequestRepository;
import ir.dotin.cardprintrequest.service.PrintRequestService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = {PrintRequestControllerMVC.class},excludeAutoConfiguration = SecurityAutoConfiguration.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
public class PrintRequestMVCControllerTest {

    @MockBean
    private PrintRequestService printRequestService;

    @MockBean
    private PrintRequestControllerMVC printRequestControllerMVC;

    @MockBean
    PrintRequestRepository printRequestRepository;

    @MockBean
    CustomPrintRequestRepository cpr;

//    @MockBean
//    Model model;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void getAllCardPrintRequest() throws Exception {

        String response = "show-all-requests.jsp";
        when(printRequestControllerMVC.ShowAll(any(Model.class))).thenReturn(response);

        mvc.perform(get("/printrequests/all").param("m","model"))
                .andExpect(status().isOk())
                .andExpect(view().name("show-all-requests.jsp"))
                .andExpect(forwardedUrl("/WEB-INF/view/show-all-requests.jsp"))
                .andExpect(model().attribute("printRequests", response));
    }
}
