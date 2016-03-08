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

    private double duration;

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

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
