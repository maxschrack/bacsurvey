package bac.rest.analysis;

import bac.model.enums.EProcessModelQuestionType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcessModelQuestionRest {

    @JsonProperty("questionId")
    private Long questionId;

    @JsonProperty("status")
    private EProcessModelQuestionType status;

    @JsonProperty("label")
    private String label;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public EProcessModelQuestionType getStatus() {
        return status;
    }

    public void setStatus(EProcessModelQuestionType status) {
        this.status = status;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
