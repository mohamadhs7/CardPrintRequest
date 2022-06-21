package ir.dotin.cardprintrequest.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
public class ActivityLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "c_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private String date;

    @Column(name = "c_operationName")
    private String operationName;

    @Column(name = "c_userPersonnelCode")
    private String userPersonnelCode;

    @Column(name = "c_panNumber")
    private String panNumber;

    @Value("${application.type}")
    private String applicationType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getUserPersonnelCode() {
        return userPersonnelCode;
    }

    public void setUserPersonnelCode(String userPersonnelCode) {
        this.userPersonnelCode = userPersonnelCode;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }
}
