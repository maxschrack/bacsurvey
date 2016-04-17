package bac.repository;

import bac.model.Log;
import bac.model.Page;
import bac.model.Participant;
import bac.model.Questionnaire;
import bac.model.enums.ELogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    List<Log> findByQuestionnaire(Questionnaire questionnaire);

    @Query(value = "SELECT DISTINCT(l.participant_id) FROM Log l WHERE l.questionnaire_id = ?1 ORDER BY l.participant_id", nativeQuery = true)
    List<BigInteger> findByQuestionnaireDistinct(Long questionnaireId);

    List<Log> findByObjectIdAndType(Long id, ELogType type);

    @Query("SELECT COUNT(l) FROM Log l WHERE l.objectId = ?1 AND l.type=?2")
    Long getVisitsPerPage(Long objectId, ELogType type);

    /*@Query(value = "SELECT AVG(((DATE_PART('day', l.end_date - l.start_date) * 24 + " +
            "DATE_PART('hour', l.end_date - start_date)) * 60 + " +
            "DATE_PART('minute', l.end_date - start_date)) * 60 + " +
            "DATE_PART('second', l.end_date - start_date)) " +
            "FROM Log l WHERE l.end_date IS NOT NULL AND l.object_id= ?1 AND l.type =?2", nativeQuery = true)*/
    @Query(value = "SELECT AVG(l.duration) " +
            "FROM Log l WHERE l.end_date IS NOT NULL AND l.object_id= ?1 AND l.type =?2 AND l.duration != 0", nativeQuery = true)
    Double getAvgTimePerObject(Long objectID, String type);

    List<Log> findByQuestionnaireAndType(Questionnaire questionnaire, ELogType type);

    List<Log> findByQuestionnaireAndTypeAndParticipantOrderByStartDateAsc(Questionnaire questionnaire, ELogType type, Participant participant);

    List<Log> findByQuestionnaireAndTypeAndParticipantOrderByObjectIdAsc(Questionnaire questionnaire, ELogType type, Participant participant);

    Log findByQuestionnaireAndTypeAndParticipantAndObjectId(Questionnaire questionnaire, ELogType type, Participant participant, Long objectID);

}
