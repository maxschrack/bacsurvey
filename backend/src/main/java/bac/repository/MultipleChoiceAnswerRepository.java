package bac.repository;

import bac.model.MultipleChoice;
import bac.model.MultipleChoiceAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MultipleChoiceAnswerRepository extends JpaRepository<MultipleChoiceAnswer, Long> {

    @Query("SELECT a FROM MultipleChoiceAnswer a WHERE a.multipleChoice.id = ?1 ")
    List<MultipleChoiceAnswer> findByMultipleChoice(Long id);

    @Modifying
    @Transactional
    @Query("DELETE from MultipleChoiceAnswer a WHERE a.multipleChoice = ?1")
    void deleteAllAnswersPerQuestion(MultipleChoice multipleChoice);
}
