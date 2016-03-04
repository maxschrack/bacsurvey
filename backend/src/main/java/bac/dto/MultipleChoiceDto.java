package bac.dto;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceDto extends QuestionDto {

    private boolean isSingleChoice;

    private List<MultipleChoiceAnswerDto> answers;

    public MultipleChoiceDto() {
        this.answers = new ArrayList<>();
    }

    public boolean getIsSingleChoice() {
        return isSingleChoice;
    }

    public void setIsSingleChoice(boolean isSingleChoice) {
        this.isSingleChoice = isSingleChoice;
    }

    public List<MultipleChoiceAnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<MultipleChoiceAnswerDto> answers) {
        this.answers = answers;
    }

    @Override
    public String getDisplayName() {
        return "multiple choice";
    }
}
