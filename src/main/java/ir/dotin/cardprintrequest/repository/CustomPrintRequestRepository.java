package ir.dotin.cardprintrequest.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomPrintRequestRepository {
    List<String> getIpAddressesByBranchCode(String BranchCode);
}
