package bac.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public abstract class EntityModelRest extends BaseModelRest implements IdentifiableRest {

    @JsonProperty("id")
    @ApiModelProperty(required = true, notes = "readonly")
    private Long selfId;

    public long getSelfId() {
        return selfId;
    }

    public void setSelfId(Long selfId) {
        this.selfId = selfId;
    }
}
