package bac.repository;

import bac.model.Log;
import bac.model.enums.ELogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    List<Log> findByObjectIdAndType(Long id, ELogType type);
}
