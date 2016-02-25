package bac.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name="answer")
public class Answer extends bac.model.Entity{

    @Id
    @Column(unique=true, nullable=false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_answer_id")
    @SequenceGenerator(initialValue = 1, name = "seq_answer_id", sequenceName = "seq_answer_id")
    private Long id;

    @Column(name="answer")
    private String answer;

    @ManyToOne
    @JoinColumn(name="participant_id", referencedColumnName="id")
    private Participant participant;

    @ManyToOne
    @JoinColumn(name="question_id", referencedColumnName="id")
    private Question question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
