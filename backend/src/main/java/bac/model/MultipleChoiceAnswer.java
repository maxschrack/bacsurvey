package bac.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.Entity;

import javax.persistence.*;

@Entity
@Table(name="multiple_choice_answer")
public class MultipleChoiceAnswer extends bac.model.Entity{

    @Id
    @Column(unique=true, nullable=false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_multiple_choice_answer_id")
    @SequenceGenerator(initialValue = 1, name = "seq_multiple_choice_answer_id", sequenceName = "seq_multiple_choice_answer_id")
    private Long id;

    @Column(name="text", nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "multiple_choice_id", referencedColumnName = "id")
    private MultipleChoice multipleChoice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MultipleChoice getMultipleChoice() {
        return multipleChoice;
    }

    public void setMultipleChoice(MultipleChoice multipleChoice) {
        this.multipleChoice = multipleChoice;
    }
}
