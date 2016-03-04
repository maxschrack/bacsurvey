package bac.rest;


import com.fasterxml.jackson.annotation.JsonProperty;

public class MultipleChoiceAnswerRest extends EntityModelRest{

    @JsonProperty("text")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
