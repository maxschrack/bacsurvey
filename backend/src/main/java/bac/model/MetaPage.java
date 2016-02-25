package bac.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by max on 09/02/16.
 */
@Entity
@Table(name="meta_page")
@Where(clause = "deleted='f'")
@SQLDelete(sql="update meta_page set deleted = 't' where id = ?")
public class MetaPage extends bac.model.Entity{

    @Id
    @Column(unique=true, nullable=false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_metapage_id")
    @SequenceGenerator(initialValue = 1, name = "seq_metapage_id", sequenceName = "seq_metapage_id")
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="text", nullable = false)
    private String text;

    @Column(name="deleted")
    private boolean deleted;

    @OneToOne
    @JoinColumn(name="questionnaire_id", referencedColumnName = "id")
    private Questionnaire questionnaire;

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
}
