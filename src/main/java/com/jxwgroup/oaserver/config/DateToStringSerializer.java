package com.jxwgroup.oaserver.config;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.util.Date;

public class DateToStringSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(null != date){
            jsonGenerator.writeString( DateFormatUtils.format(date,"yyyy-MM-dd HH:mm:ss"));
        }else{
            jsonGenerator.writeNull();
        }
    }
}
