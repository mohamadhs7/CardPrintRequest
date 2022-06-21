package ir.dotin.cardprintrequest.repository;

import ir.dotin.cardprintrequest.models.PrintRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Primary
@Repository
public class CustomPrintRequestRepositoryImpl implements CustomPrintRequestRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<String> getIpAddressesByBranchCode(String branchCode) {
    Query query = entityManager.createQuery("select pr.id.ipAddress from PrintRequest as pr where pr.id.branchCode =: branchCode "
            );
    query.setParameter("branchCode",branchCode);
    List<String> ipAddresses = query.getResultList();
    return ipAddresses;
    }




}
