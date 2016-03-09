package bac.rest.analysis;

import bac.model.enums.EQuestionType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class AnalyzeMultipleChoiceRest extends AnalyzeAnswerRest {

    @JsonProperty("type")
    private EQuestionType type;

    @JsonProperty("answers")
    private List<AnswerCounterRest> answers;

    public AnalyzeMultipleChoiceRest(){
        this.answers = new ArrayList<>();
    }

    @Override
    public EQuestionType getType() {
        return type;
    }

    @Override
    public void setType(EQuestionType type) {
        this.type = type;
    }

    public List<AnswerCounterRest> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerCounterRest> answers) {
        this.answers = answers;
    }
}
