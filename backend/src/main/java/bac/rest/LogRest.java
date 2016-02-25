package bac.rest;

import bac.model.enums.ELogType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class LogRest extends EntityModelRest{

    @JsonProperty("startDate")
    private Date startDate;

    @JsonProperty("endDate")
    private Date endDate;

    @JsonProperty("objectId")
    private Long objectId;

    @JsonProperty("type")
    private ELogType type;

    @JsonProperty("questionnaireId")
    private Long questionnaireId;

    @JsonProperty("participantId")
    private Long participantId;

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
}
