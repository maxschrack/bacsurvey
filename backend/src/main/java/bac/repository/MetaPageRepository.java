package bac.repository;

import bac.model.MetaPage;
import bac.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaPageRepository extends JpaRepository<MetaPage, Long> {

}
