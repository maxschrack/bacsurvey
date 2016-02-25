package bac.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceRest extends QuestionRest {

    @JsonProperty("isSingleChoice")
    private boolean isSingleChoice;

    @JsonProperty("answers")
    private List<String> answers = new ArrayList<>();;

    public boolean getIsSingleChoice() {
        return isSingleChoice;
    }

    public void setIsSingleChoice(boolean isSingleChoice) {
        this.isSingleChoice = isSingleChoice;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
