package bac.rest.analysis;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by max on 17/04/16.
 */
public class ColorRest {

    @JsonProperty("value")
    private String value;

    @JsonProperty("color")
    private String color;

    public ColorRest() {
    }

    public ColorRest(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
