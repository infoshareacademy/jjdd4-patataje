package com.hydrozagadka.Beans;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hydrozagadka.History;
import com.hydrozagadka.dao.HistoryDao;
import com.hydrozagadka.dao.WaterContainerDao;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
@RequestScoped
public class JsonParserBean<T>  {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Inject
    private HistoryDao historyDao;

    @Inject
   private WaterContainerDao waterContainerDao;

    List<History> histories;

    public String parseToJson(List<T> dataToParse) throws JsonProcessingException {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return objectMapper.writeValueAsString(dataToParse);
    }
}
