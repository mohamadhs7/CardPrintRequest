package ir.dotin.cardprintrequest.printrequest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.dotin.cardprintrequest.controllers.PrintRequestController;
import ir.dotin.cardprintrequest.models.PrintRequest;
import ir.dotin.cardprintrequest.models.PrintRequestId;
import ir.dotin.cardprintrequest.repository.CustomPrintRequestRepository;
import ir.dotin.cardprintrequest.repository.PrintRequestRepository;
import ir.dotin.cardprintrequest.service.PrintRequestService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {PrintRequestController.class},excludeAutoConfiguration = SecurityAutoConfiguration.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
public class PrintRequestRestControllerTest {

    @MockBean
    private PrintRequestService printRequestService;

    @MockBean
    private PrintRequestController printRequestController;

    @MockBean
    PrintRequestRepository printRequestRepository;

    @MockBean
    CustomPrintRequestRepository cpr;

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
    public void createNewPR() throws Exception {
        PrintRequest printRequest = new PrintRequest();
        printRequest.setPersonnelCode("2144578");
        printRequest.setCardPan("6395991145417077");
        printRequest.setIssuedDate("10-10-2020");
        PrintRequestId printRequestId = new PrintRequestId();
        printRequestId.setBranchCode("121215");
        printRequestId.setIpAddress("10.20.12.35");
        printRequest.setId(printRequestId);


        when(printRequestController.createNewPR(any(PrintRequest.class))).thenReturn(printRequest);

        mvc.perform(post("/root/create")
                        .content(asJson(printRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

    public static String asJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
