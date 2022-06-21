package ir.dotin.cardprintrequest.controllers;

import java.util.List;

import ir.dotin.cardprintrequest.models.PrintRequest;
import ir.dotin.cardprintrequest.repository.PrintRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/printrequests")
public class PrintRequestControllerMVC {

    @Autowired
    PrintRequestRepository prRepo;

    @GetMapping("/all")
    public String ShowAll(Model m) {
        List<PrintRequest> printRequests = prRepo.findAll();
        m.addAttribute("printRequests",printRequests);
        return "show-all-requests.jsp";
    }

    @GetMapping("/new")
    public ModelAndView newRequest(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("printRequest",new PrintRequest());
        modelAndView.setViewName("new-print-request.jsp");
        return modelAndView;
    }
    @PostMapping("/save")
    public String CreateRequest(Model model , @ModelAttribute("printRequest") PrintRequest printRequest ) {
        prRepo.save(printRequest);
        return"redirect:all";
    }
}
