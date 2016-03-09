package bac.rest.analysis;

import bac.model.enums.EQuestionType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public abstract class AnalyzeAnswerRest {

    @JsonProperty("type")
    private EQuestionType type;

    public EQuestionType getType() {
        return type;
    }

    public void setType(EQuestionType type) {
        this.type = type;
    }

}
