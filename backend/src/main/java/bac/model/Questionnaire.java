package bac.model;

import bac.model.enums.EStatus;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import javax.persistence.Entity;
import java.util.*;

/**
 * Created by max on 08/02/16.
 */
@Entity
@Table(name="questionnaire")
@Where(clause = "deleted='f'")
@SQLDelete(sql="update questionnaire set deleted = 't' where id = ?")
public class Questionnaire extends bac.model.Entity{

    @Id
    @Column(unique=true, nullable=false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_questionnaire_id")
    @SequenceGenerator(initialValue = 1, name = "seq_questionnaire_id", sequenceName = "seq_questionnaire_id")
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created", nullable = false)
    private Date created;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EStatus status;

    @Column(name = "progressbar")
    private boolean progressbar;

    @Column(name="deleted")
    private boolean deleted;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "questionnaire")
    @OrderColumn(name = "number")
    private List<Page> pages;

    @OneToOne
    @JoinColumn(name = "start_page")
    private MetaPage startPage;

    @OneToOne
    @JoinColumn(name = "end_page")
    private MetaPage endPage;

    @OneToMany(mappedBy = "questionnaire")
    private List<Participant> participant;

    public Questionnaire(){
        this.pages = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public boolean getProgressbar() {
        return progressbar;
    }

    public void setProgressbar(boolean progressbar) {
        this.progressbar = progressbar;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public MetaPage getStartPage() {
        return startPage;
    }

    public void setStartPage(MetaPage startPage) {
        this.startPage = startPage;
    }

    public MetaPage getEndPage() {
        return endPage;
    }

    public void setEndPage(MetaPage endPage) {
        this.endPage = endPage;
    }

    public List<Participant> getParticipant() {
        return participant;
    }

    public void setParticipant(List<Participant> participant) {
        this.participant = participant;
    }
}
