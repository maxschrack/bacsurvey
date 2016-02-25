package bac.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PageDto extends EntityDto {

    private Long id;

    private String title;

    private String text;

    private int number;

    private boolean deleted;

    // Foreign Keys
    private Long questionnaireId;

    private List<Long> questionIds;

    public PageDto() {
        this.questionIds = new ArrayList<>();
    }

    public PageDto(Long id) {
        this.id = id;
    }

    @Override
    public String getDisplayName() {
        return "page";
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public List<Long> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Long> questionIds) {
        this.questionIds = questionIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageDto pageDto = (PageDto) o;

        if (number != pageDto.number) return false;
        if (deleted != pageDto.deleted) return false;
        if (id != null ? !id.equals(pageDto.id) : pageDto.id != null) return false;
        if (title != null ? !title.equals(pageDto.title) : pageDto.title != null) return false;
        if (text != null ? !text.equals(pageDto.text) : pageDto.text != null) return false;
        if (questionnaireId != null ? !questionnaireId.equals(pageDto.questionnaireId) : pageDto.questionnaireId != null)
            return false;
        return !(questionIds != null ? !questionIds.equals(pageDto.questionIds) : pageDto.questionIds != null);

    }
}
