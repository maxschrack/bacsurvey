package bac.repository;

import bac.model.Questionnaire;
import bac.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {

    @Query("SELECT q FROM Questionnaire q INNER JOIN q.user u WHERE u = ?1 ")
    List<Questionnaire> findByUser(User finder);
}
