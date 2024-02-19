package com.jxwgroup.oaserver.config;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class NumericBooleanSerializer extends JsonDeserializer<Integer> {

    @Override
    public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String value = jsonParser.getValueAsString();
        if(StringUtils.isEmpty(value)){
            return null;
        } else if("true,false".contains(value)){
            return jsonParser.getBooleanValue()? 1 : 0;
        }else{
            try {
                return Integer.parseInt(value);
            }catch (NumberFormatException e){
                return (int)Double.parseDouble(value);
            }
//            return jsonParser.getIntValue();
        }
    }
}
