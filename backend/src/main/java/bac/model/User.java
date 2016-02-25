package bac.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by max on 08/02/16.
 */
@Entity
@Table(name="system_user")
@Where(clause = "deleted='f'")
@SQLDelete(sql="update system_user set deleted = 't' where id = ?")
public class User extends bac.model.Entity{

    @Id
    @Column(unique=true, nullable=false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_system_user_id")
    @SequenceGenerator(initialValue = 1, name = "seq_system_user_id", sequenceName = "seq_system_user_id")
    private Long id;

    @Column(name="email", unique=true)
    private String email;

    @Column(name="password")
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="registration_date")
    private Date registrationDate;

    @Column(name="deleted")
    private boolean deleted;

    @OneToMany(mappedBy = "user")
    private Set<Questionnaire> questionnaires;

    public User(){
        this.questionnaires = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Set<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(Set<Questionnaire> questionnaires) {
        this.questionnaires = questionnaires;
    }
}
