package bac.rest;


import com.fasterxml.jackson.annotation.JsonProperty;

public class MultipleChoiceAnswerRest {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("text")
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
