package bac.rest;


import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class QuestionRest extends EntityModelRest{

    @JsonProperty("text")
    private String text;

    @JsonProperty("mandatory")
    private boolean mandatory;

    @JsonProperty("position")
    private int position;

    @JsonProperty("deleted")
    private boolean deleted;

    @JsonProperty("pageId")
    private Long pageId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }
}
