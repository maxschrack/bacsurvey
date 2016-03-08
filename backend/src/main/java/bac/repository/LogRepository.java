package bac.repository;

import bac.model.Log;
import bac.model.Page;
import bac.model.enums.ELogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    List<Log> findByObjectIdAndType(Long id, ELogType type);

    @Query("SELECT COUNT(l) FROM Log l WHERE l.objectId = ?1 AND l.type=?2")
    Long getVisitsPerPage(Long objectId, ELogType type);

    @Query(value = "SELECT AVG(((DATE_PART('day', l.end_date - l.start_date) * 24 + " +
            "DATE_PART('hour', l.end_date - start_date)) * 60 + " +
            "DATE_PART('minute', l.end_date - start_date)) * 60 + " +
            "DATE_PART('second', l.end_date - start_date)) " +
            "FROM Log l WHERE l.end_date IS NOT NULL AND l.object_id= ?1 AND l.type =?2", nativeQuery = true)
    Integer getTimePerPage(Long objectID, String type);
}
