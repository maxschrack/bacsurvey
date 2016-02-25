package bac.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by max on 13/02/16.
 */
public class ParticipantDto extends EntityDto {

    private Long id;

    private String email;

    private String password;

    private String ipAddress;

    private Date creationDate;

    private boolean deleted;

    // Foreign Keys
    private Long questionnaireId;

    private Set<Long> answerIds;

    private Set<Long> logIds;

    public ParticipantDto(Long id) {
        this.id = id;
        this.answerIds = new HashSet<>();
        this.logIds = new HashSet<>();
    }

    public ParticipantDto(){
        this.answerIds = new HashSet<>();
        this.logIds = new HashSet<>();
    }
    @Override
    public String getDisplayName() {
        return "participant";
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Set<Long> getAnswerIds() {
        return answerIds;
    }

    public void setAnswerIds(Set<Long> answerIds) {
        this.answerIds = answerIds;
    }

    public Set<Long> getLogIds() {
        return logIds;
    }

    public void setLogIds(Set<Long> logIds) {
        this.logIds = logIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParticipantDto that = (ParticipantDto) o;

        if (deleted != that.deleted) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (ipAddress != null ? !ipAddress.equals(that.ipAddress) : that.ipAddress != null) return false;
        if (answerIds != null ? !answerIds.equals(that.answerIds) : that.answerIds != null) return false;
        return !(logIds != null ? !logIds.equals(that.logIds) : that.logIds != null);

    }
}
