package bac.repository;

import bac.model.Answer;
import bac.model.Participant;
import bac.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByQuestion(Question finder);

    List<Answer> findByParticipant(Participant finder);
}
