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

    List<Answer> findByQuestion(Question finder);

    List<Answer> findByParticipant(Participant finder);

    @Query(value = "SELECT COUNT(*) from answer a WHERE a.answer LIKE('') AND a.question_id = ?1", nativeQuery = true)
    Integer countNonResponsesPerQuestion(Long questionId);

    @Query(value = "SELECT COUNT(*) from answer a WHERE a.answer NOT LIKE('') AND a.question_id = ?1", nativeQuery = true)
    Integer countResponsesPerQuestion(Long questionId);
}
