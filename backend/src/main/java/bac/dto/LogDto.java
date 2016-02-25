package bac.dto;

import bac.model.enums.ELogType;

import java.util.Date;

public class LogDto extends EntityDto {

    private Long id;

    private Date startDate;

    private Date endDate;

    private Long objectId;

    private ELogType type;

    // Foreign Keys
    private Long questionnaireId;

    private Long participantId;

    @Override
    public String getDisplayName() {
        return "log";
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public ELogType getType() {
        return type;
    }

    public void setType(ELogType type) {
        this.type = type;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogDto logDto = (LogDto) o;

        if (id != null ? !id.equals(logDto.id) : logDto.id != null) return false;
        if (startDate != null ? !startDate.equals(logDto.startDate) : logDto.startDate != null) return false;
        if (endDate != null ? !endDate.equals(logDto.endDate) : logDto.endDate != null) return false;
        if (objectId != null ? !objectId.equals(logDto.objectId) : logDto.objectId != null) return false;
        if (type != logDto.type) return false;
        if (questionnaireId != null ? !questionnaireId.equals(logDto.questionnaireId) : logDto.questionnaireId != null)
            return false;
        return !(participantId != null ? !participantId.equals(logDto.participantId) : logDto.participantId != null);

    }
}
