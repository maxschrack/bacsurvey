package bac.dto;

/**
 * Created by max on 13/02/16.
 */
public abstract class EntityDto implements Dto{

    public abstract String getDisplayName();
    public abstract Long getId();
    public abstract void setId(Long id);

}
