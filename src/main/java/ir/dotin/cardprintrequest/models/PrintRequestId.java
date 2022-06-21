package ir.dotin.cardprintrequest.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
public class PrintRequestId implements Serializable {

    @GeneratedValue
    @Column(name = "c_branchCode",nullable = false)
    @Size(min = 3)
    @Valid
    private String branchCode;

    @GeneratedValue
    @Column(name = "c_ipAddress")
    @Pattern(regexp ="\"^([01]?\\\\d\\\\d?|2[0-4]\\\\d|25[0-5])\\\\.\"" +
            "\"([01]?\\\\d\\\\d?|2[0-4]\\\\d|25[0-5])\\\\.\" +\n" +
            "\"([01]?\\\\d\\\\d?|2[0-4]\\\\d|25[0-5])\\\\.\" +\n" +
            "\"([01]?\\\\d\\\\d?|2[0-4]\\\\d|25[0-5])$\";" ,message = "ipAddress is invalid")
    @Valid
    private String ipAddress;

    public PrintRequestId() {
    }

    public PrintRequestId(String branchCode, String ipAddress) {
        this.branchCode = branchCode;
        this.ipAddress = ipAddress;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
