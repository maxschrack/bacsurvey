package bac.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by max on 08/02/16.
 */
@Entity
@Table(name="page")
@Where(clause = "deleted='f'")
@SQLDelete(sql="update page set deleted = 't' where id = ?")
public class Page extends bac.model.Entity{

    @Id
    @Column(unique=true, nullable=false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_page_id")
    @SequenceGenerator(initialValue = 1, name = "seq_page_id", sequenceName = "seq_page_id")
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="text", nullable = false)
    private String text;

    @Column(name="number")
    private int number;

    @Column(name="deleted")
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name="questionnaire_id", referencedColumnName = "id")
    private Questionnaire questionnaire;

    @OneToMany(mappedBy = "page")
    @OrderColumn(name = "position")
    private List<Question> questions;

    public Page(){
        this.questions = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
