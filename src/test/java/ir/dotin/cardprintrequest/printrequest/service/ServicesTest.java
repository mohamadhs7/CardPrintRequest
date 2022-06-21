package ir.dotin.cardprintrequest.printrequest.service;

import ir.dotin.cardprintrequest.models.PrintRequest;
import ir.dotin.cardprintrequest.models.PrintRequestId;
import ir.dotin.cardprintrequest.repository.PrintRequestRepository;
import ir.dotin.cardprintrequest.service.PrintRequestService;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(MockitoJUnitRunner.class)
public class ServicesTest {

    @Mock
    PrintRequestRepository printRequestRepository;

    @InjectMocks
    PrintRequestService printRequestService;

    @BeforeClass
    public static void setUp() {
    }

    @Test
    public void updatePrintRequestByPersonnelCode() {
        PrintRequest printRequest = new PrintRequest();

        printRequest.setPersonnelCode("2144578");
        printRequest.setCardPan("6395991145417077");
        printRequest.setIssuedDate("10-10-2020");
        PrintRequestId printRequestId = new PrintRequestId();
        printRequestId.setBranchCode("121215");
        printRequestId.setIpAddress("10.20.12.35");
        printRequest.setId(printRequestId);

        when(printRequestRepository.save(any())).thenReturn(printRequest);

        PrintRequest newPrintRequest = new PrintRequest();
        newPrintRequest.setCardPan("6104338979652105");
        newPrintRequest.setId(printRequestId);

        when(printRequestRepository.getByPersonnelCode(any())).thenReturn(printRequest);
        when(printRequestRepository.save(any())).thenReturn(newPrintRequest);

        PrintRequest updatedPrintRequest = printRequestService.updatePrintRequestByPersonnelCode(printRequest.getPersonnelCode(), newPrintRequest);

        assertEquals(newPrintRequest.getId().getBranchCode(), updatedPrintRequest.getId().getBranchCode());

    }

    @Test
    public void partialUpdatePrintRequestByPersonnelCode() {
        PrintRequest printRequest = new PrintRequest();

        printRequest.setPersonnelCode("2144578");
        printRequest.setCardPan("6395991145417077");
        printRequest.setIssuedDate("10-10-2020");
        PrintRequestId printRequestId = new PrintRequestId();
        printRequestId.setBranchCode("121215");
        printRequestId.setIpAddress("10.20.12.35");
        printRequest.setId(printRequestId);

        when(printRequestRepository.save(any())).thenReturn(printRequest);

        PrintRequest newPrintRequest = new PrintRequest();
        newPrintRequest.setCardPan("6104338979652105");
        newPrintRequest.setIssuedDate("11-12-2022");
        newPrintRequest.setId(printRequestId);

        when(printRequestRepository.getByPersonnelCode(any())).thenReturn(printRequest);
        when(printRequestRepository.save(any())).thenReturn(newPrintRequest);

        PrintRequest updatedPrintRequest = printRequestService.partialUpdatePrintRequestByPersonnelCode(printRequest.getPersonnelCode(), newPrintRequest);

        assertEquals(newPrintRequest.getId().getBranchCode(), updatedPrintRequest.getId().getBranchCode());

    }

}
