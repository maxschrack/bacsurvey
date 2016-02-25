package bac.rest;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by max on 16/02/16.
 */
public abstract class BaseModelRest extends ResourceSupport implements Serializable{

    @Override
    @ApiModelProperty(hidden = true)
    public List<Link> getLinks(){
        return super.getLinks();
    }
}
