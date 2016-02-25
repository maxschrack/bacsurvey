package bac.rest;

import bac.model.enums.EValidationType;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by max on 22/02/16.
 */
public class OpenQuestionRest extends QuestionRest {

    @JsonProperty("validationType")
    private EValidationType validationType;

    @JsonProperty("isLong")
    private boolean isLong;

    public EValidationType getValidationType() {
        return validationType;
    }

    public void setValidationType(EValidationType validationType) {
        this.validationType = validationType;
    }

    public boolean getIsLong() {
        return isLong;
    }

    public void setIsLong(boolean isLong) {
        this.isLong = isLong;
    }
}
