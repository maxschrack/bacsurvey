package bac.dto;

/**
 * Created by max on 13/02/16.
 */
public class MultipleChoiceAnswerDto extends EntityDto {

    private Long id;

    private String text;

    // Foreign Key
    private Long multipleChoiceId;
    @Override
    public String getDisplayName() {
        return "multiple choice answer";
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getMultipleChoiceId() {
        return multipleChoiceId;
    }

    public void setMultipleChoiceId(Long multipleChoiceId) {
        this.multipleChoiceId = multipleChoiceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MultipleChoiceAnswerDto that = (MultipleChoiceAnswerDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        return !(multipleChoiceId != null ? !multipleChoiceId.equals(that.multipleChoiceId) : that.multipleChoiceId != null);

    }
}
