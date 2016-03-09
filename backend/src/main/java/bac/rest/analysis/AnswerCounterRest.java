package bac.rest.analysis;

import bac.model.enums.EQuestionType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnswerCounterRest {

    @JsonProperty("answer")
    private String answer;

    @JsonProperty("counter")
    private Long counter;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getCounter() {
        return counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }
}
