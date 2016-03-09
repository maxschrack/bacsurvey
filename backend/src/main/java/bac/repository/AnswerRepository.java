package bac.repository;

import bac.model.Answer;
import bac.model.Participant;
import bac.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query(value = "SELECT * FROM Answer a WHERE a.question_id = ?1 AND answer NOT LIKE('')", nativeQuery = true)
    List<Answer> getAnswersNotNullPerQuestion(Long questionId);

    List<Answer> findByParticipant(Participant finder);

    @Query(value = "SELECT COUNT(*) from answer a WHERE a.answer LIKE('') AND a.question_id = ?1", nativeQuery = true)
    Integer countNonResponsesPerQuestion(Long questionId);

    @Query(value = "SELECT COUNT(*) from answer a WHERE a.answer NOT LIKE('') AND a.question_id = ?1", nativeQuery = true)
    Integer countResponsesPerQuestion(Long questionId);

    /*@Query(value = "SELECT a.answer, COUNT(*) FROM answer a WHERE a.question_id = ?1 GROUP BY a.answer", nativeQuery = true)
    List<Object[]> getAllAnswersGroupByAnswer(Long questionId);*/

    @Query(value = "SELECT COUNT(*) FROM answer a WHERE a.question_id = ?1 AND answer LIKE(?2)", nativeQuery = true)
    Long getAnswerCounter(Long questionId, String answerText);
}
