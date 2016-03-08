package bac.rest.analysis;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseStatisticRest {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("text")
    private String text;

    @JsonProperty("counterResponse")
    private Integer counterResponse;

    @JsonProperty("counterNonResponse")
    private Integer counterNonResponse;

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

    public Integer getCounterResponse() {
        return counterResponse;
    }

    public void setCounterResponse(Integer counterResponse) {
        this.counterResponse = counterResponse;
    }

    public Integer getCounterNonResponse() {
        return counterNonResponse;
    }

    public void setCounterNonResponse(Integer counterNonResponse) {
        this.counterNonResponse = counterNonResponse;
    }
}
