package ir.dotin.cardprintrequest.repository;

import ir.dotin.cardprintrequest.models.ActivityLog;
import org.springframework.data.repository.CrudRepository;

public interface ActivityLogRepository extends CrudRepository<ActivityLog,Long> {
}
