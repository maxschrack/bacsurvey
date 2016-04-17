package bac.rest.analysis;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ProcessModelRest {

    @JsonProperty("participantId")
    private Long participantId;

    @JsonProperty("questions")
    private List<ProcessModelQuestionRest> questions;

    public ProcessModelRest() {
        this.questions = new ArrayList<>();
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public List<ProcessModelQuestionRest> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ProcessModelQuestionRest> questions) {
        this.questions = questions;
    }
}
