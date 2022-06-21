package ir.dotin.cardprintrequest.service;

import ir.dotin.cardprintrequest.exceptions.PrintRequestNotFoundException;
import ir.dotin.cardprintrequest.models.PrintRequest;
import ir.dotin.cardprintrequest.repository.PrintRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrintRequestService {

    @Autowired
    PrintRequestRepository preRepo;

    @Transactional()
    public PrintRequest updatePrintRequestByPersonnelCode(String personnelCode , PrintRequest printRequest) {
       PrintRequest pr = preRepo.getByPersonnelCode(personnelCode);
       if (pr == null) {
           throw new PrintRequestNotFoundException("PrintRequest Not Found");
       }
       else {
           pr.setCardPan(printRequest.getCardPan());
           return preRepo.save(pr);
       }
    }

    public PrintRequest partialUpdatePrintRequestByPersonnelCode(String personnelCode , PrintRequest printRequest) {
        PrintRequest pr = preRepo.getByPersonnelCode(personnelCode);
        if (pr == null) {
            throw new PrintRequestNotFoundException("PrintRequest Not Found");
        }
        else {
            pr.setCardPan(printRequest.getCardPan());
            pr.setIssuedDate(printRequest.getIssuedDate());
            return preRepo.save(pr);
        }
    }

    @Transactional(readOnly = true)
    public List<PrintRequest> getPrintRequestByPersonnelCode(String personnelCode){
        return preRepo.getByCardPan(personnelCode);
    }


}
