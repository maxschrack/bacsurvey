package bac.dto;

import bac.model.enums.EStatus;

import java.util.*;

/**
 * Created by max on 13/02/16.
 */
public class QuestionnaireDto extends EntityDto{

    private Long id;

    private String name;

    private Date created;

    private EStatus status;

    private boolean progressbar;

    private boolean deleted;

    // Foreign keys
    private Long userId;

    private Long startPageId;

    private Long endPageId;

    private List<Long> pageIds;

    public QuestionnaireDto(){
        this.pageIds = new ArrayList<>();
    }

    public QuestionnaireDto(Long id){
        this.id = id;
        this.pageIds = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public boolean getProgressbar() {
        return progressbar;
    }

    public void setProgressbar(boolean progressbar) {
        this.progressbar = progressbar;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStartPageId() {
        return startPageId;
    }

    public void setStartPageId(Long startPageId) {
        this.startPageId = startPageId;
    }

    public Long getEndPageId() {
        return endPageId;
    }

    public void setEndPageId(Long endPageId) {
        this.endPageId = endPageId;
    }

    public List<Long> getPageIds() {
        return pageIds;
    }

    public void setPageIds(List<Long> pageIds) {
        this.pageIds = pageIds;
    }

    @Override
    public String getDisplayName() {
        return "questionnaire";
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionnaireDto that = (QuestionnaireDto) o;

        if (progressbar != that.progressbar) return false;
        if (deleted != that.deleted) return false;
        if (!id.equals(that.id)) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (status != that.status) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (startPageId != null ? !startPageId.equals(that.startPageId) : that.startPageId != null) return false;
        if (endPageId != null ? !endPageId.equals(that.endPageId) : that.endPageId != null) return false;
        return !(pageIds != null ? !pageIds.equals(that.pageIds) : that.pageIds != null);

    }
}
