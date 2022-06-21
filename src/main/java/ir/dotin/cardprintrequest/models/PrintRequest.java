package ir.dotin.cardprintrequest.models;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "t_PrintRequest")
public class PrintRequest {

    private static final Logger logger=LoggerFactory.getLogger(PrintRequest.class);

    @EmbeddedId
    @Column(name = "c_PrintRequestId")
    private PrintRequestId id;

    @Column(name = "c_personnelCode",nullable = false)
    @Size(min = 5,max = 10)
    @Valid
    private String personnelCode;


    @Column(name = "c_cardPan")
    private String cardPan;


    @Column(name = "c_issuedDate")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Valid
    private String issuedDate;


    public PrintRequest() {}

    public PrintRequest(PrintRequestId id,String personnelCode, String cardPan, String issuedDate) {
        this.id = id ;
        this.personnelCode = personnelCode;
        this.cardPan = cardPan;
        this.issuedDate = issuedDate;
    }

    @PostPersist
    public void loggingAfterPerssist (){
        logger.info("----An Object  "+this.id+" Saved In DB From PrintRequestEntity---  ");
    }

    public PrintRequestId getId() {
        return id;
    }

    public void setId(PrintRequestId id) {
        this.id = id;
    }

    public String getPersonnelCode() {
        return personnelCode;
    }

    public void setPersonnelCode(String personnelCode) {
        this.personnelCode = personnelCode;
    }

    public String getCardPan() {
        return cardPan;
    }

    public void setCardPan(String cardPan) {
        this.cardPan = cardPan;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    @Override
    public String toString() {
        return "PrintRequest{" +
                "id=" + id +
                ", personnelCode='" + personnelCode + '\'' +
                ", cardPan='" + cardPan + '\'' +
                ", issuedDate='" + issuedDate + '\'' +
                '}';
    }
}
