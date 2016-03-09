package bac.rest.analysis;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class AnalyzeOpenQuestionRest extends AnalyzeAnswerRest {

    @JsonProperty("answers")
    List<String> answers;

    public AnalyzeOpenQuestionRest(){
        this.answers = new ArrayList<>();
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
