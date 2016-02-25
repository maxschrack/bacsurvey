package bac.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnswerRest extends EntityModelRest{

    @JsonProperty("answer")
    private String answer;

    @JsonProperty("participantId")
    private Long participantId;

    @JsonProperty("questionId")
    private Long questionId;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
