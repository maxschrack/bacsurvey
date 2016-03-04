package bac.repository;

import bac.model.Page;
import bac.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q INNER JOIN q.page p WHERE p = ?1 order by q.id")
    List<Question> findByPage(Page finder);
}
