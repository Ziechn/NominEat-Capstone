
package com.techelevator.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "open",
    "hours_type",
    "is_open_now"
})
@Generated("jsonschema2pojo")
public class Hour {

    @JsonProperty("open")
    private List<Open> open;
    @JsonProperty("hours_type")
    private String hoursType;
    @JsonProperty("is_open_now")
    private Boolean isOpenNow;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("open")
    public List<Open> getOpen() {
        return open;
    }

    @JsonProperty("open")
    public void setOpen(List<Open> open) {
        this.open = open;
    }

    @JsonProperty("hours_type")
    public String getHoursType() {
        return hoursType;
    }

    @JsonProperty("hours_type")
    public void setHoursType(String hoursType) {
        this.hoursType = hoursType;
    }

    @JsonProperty("is_open_now")
    public Boolean getIsOpenNow() {
        return isOpenNow;
    }

    @JsonProperty("is_open_now")
    public void setIsOpenNow(Boolean isOpenNow) {
        this.isOpenNow = isOpenNow;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
