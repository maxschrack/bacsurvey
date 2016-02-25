package bac.rest;

import bac.model.enums.EStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by max on 16/02/16.
 */
public class QuestionnaireRest extends EntityModelRest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("created")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="UTC")
    private Date created;

    @JsonProperty("status")
    private EStatus status;

    @JsonProperty("progressbar")
    private boolean progressbar;

    @JsonProperty("deleted")
    private boolean deleted;

    @JsonProperty("userId")
    private Long userId;

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
}
