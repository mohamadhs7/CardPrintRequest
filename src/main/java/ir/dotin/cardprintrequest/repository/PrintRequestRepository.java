package ir.dotin.cardprintrequest.repository;

import ir.dotin.cardprintrequest.models.PrintRequest;
import ir.dotin.cardprintrequest.models.PrintRequestId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "t_PrintRequest", path = "t_PrintRequest")
public interface PrintRequestRepository extends JpaRepository<PrintRequest, PrintRequestId> {

    void deletePrintRequestByCardPan(String cardPan);
    void deletePrintRequestByPersonnelCode(String personnelCode);
    List<PrintRequest> getByCardPan(String cardPan , Pageable pageable);
    List<PrintRequest> getByIssuedDateAndCardPan(String issuedDate , String CardPan , Pageable pageable);
    PrintRequest getByPersonnelCode(String personnelCode);
    List<PrintRequest> getByCardPan(String cardPan);


    @Transactional
    @Query(nativeQuery = true, value = "SELECT * FROM t_Print_Request as pr  "
            +" WHERE pr.c_personnel_Code = '0023060883' ")
    List<PrintRequest> getPrintRequests (Pageable pageable);

    @Transactional
    @Modifying
    @Query(nativeQuery = true , value = "update t_Print_Request set c_ip_address = '0.0.0.0' "
                +"where c_personnel_code = '1532545215'")
    void updateIpAdressToUnusedMode ();











}
