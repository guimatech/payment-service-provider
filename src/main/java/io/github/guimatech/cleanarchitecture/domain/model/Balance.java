package io.github.guimatech.cleanarchitecture.domain.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;

import java.io.Serializable;

@Builder
public class Balance implements Serializable {

    private double available;
    private double waitingFunds;

    public String toJson() throws JsonProcessingException {
        var mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return mapper.writeValueAsString(this);
    }
}
