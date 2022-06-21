package ir.dotin.cardprintrequest.controllers;

import ir.dotin.cardprintrequest.aspect.ExecuteTime;
import ir.dotin.cardprintrequest.aspect.SaveActivity;
import ir.dotin.cardprintrequest.exceptions.PrintRequestNotFoundException;
import ir.dotin.cardprintrequest.models.PrintRequest;
import ir.dotin.cardprintrequest.repository.PrintRequestRepository;
import ir.dotin.cardprintrequest.service.PrintRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/root")
public class PrintRequestController {

    @Autowired
    PrintRequestRepository printReqRepo;

    @Autowired
    PrintRequestService printReqService;

    @GetMapping("/all")
    @ExecuteTime
    public ResponseEntity<List<PrintRequest>> getAllPrintRequests() {
        return ResponseEntity.ok(printReqRepo.findAll());
    }

    @GetMapping("/get/{personnel-code}")
    public ResponseEntity<PrintRequest> getPrintRequestByPersonnelCode(@PathVariable("personnel-code") String personnelCode) {
        return ResponseEntity.ok(printReqRepo.getByPersonnelCode(personnelCode));
    }

    @PostMapping(path = "/create")
    @ResponseBody
    @SaveActivity
    public PrintRequest createNewPR(@RequestBody PrintRequest printRequest) {
        return printReqRepo.save(printRequest);
    }

    @PutMapping("/update/{personnel-code}")
    @ResponseBody
    @SaveActivity
    public ResponseEntity<PrintRequest> updatePR(@PathVariable("personnel-code") String personnelCode
            , @RequestBody PrintRequest printRequest) {
        try {
            return ResponseEntity.ok(printReqService.updatePrintRequestByPersonnelCode(personnelCode,printRequest));
        } catch (PrintRequestNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PrintRequest Not Found", e);
        }
    }

    @PatchMapping("/patch/{personnel-code}")
    @ResponseBody
    @SaveActivity
    public ResponseEntity<PrintRequest> patchCard(@PathVariable("personnel-code") String personnelCode
            , @RequestBody PrintRequest printRequest) {
        try {
            return ResponseEntity.ok(printReqService.partialUpdatePrintRequestByPersonnelCode(personnelCode,printRequest));
        } catch (PrintRequestNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PrintRequest Not Found", e);
        }
    }
}