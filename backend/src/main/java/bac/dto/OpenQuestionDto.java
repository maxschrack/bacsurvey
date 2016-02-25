package bac.dto;

import bac.model.enums.EValidationType;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by max on 13/02/16.
 */
public class OpenQuestionDto extends QuestionDto{

    private EValidationType validationType;

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

    @Override
    public String getDisplayName() {
        return "open question";
    }
}
