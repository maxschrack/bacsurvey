package bac.dto;

public class MetaPageDto extends EntityDto {

    private Long id;

    private String title;

    private String text;

    private boolean deleted;

    // Foreign Keys
    private Long questionnaireId;

    public MetaPageDto(Long id) {
        this.id = id;
    }

    public MetaPageDto() {
    }

    @Override
    public String getDisplayName() {
        return "meta page";
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetaPageDto that = (MetaPageDto) o;

        if (deleted != that.deleted) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        return !(questionnaireId != null ? !questionnaireId.equals(that.questionnaireId) : that.questionnaireId != null);

    }
}
