package ir.dotin.cardprintrequest.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomPrintRequestRepositoryimpl2 implements CustomPrintRequestRepository {

    @Override
    public List<String> getIpAddressesByBranchCode(String BranchCode) {
        return null;
    }
}
