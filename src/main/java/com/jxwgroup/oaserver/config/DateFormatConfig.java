package com.jxwgroup.oaserver.config;

import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;

public class DateFormatConfig extends DateFormat {

    private DateFormat dateFormat;

    public DateFormatConfig(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return dateFormat.format(date, toAppendTo, fieldPosition);
    }

    @Override
    public Date parse(String source, ParsePosition pos) {

        Date date = null;
        try {
            date =  DateUtils.parseDate(source,"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm","yyyy-MM-dd HH","yyyy-MM-dd","yyyy/MM/dd","yyyy/MM/dd HH","yyyy/MM/dd HH:mm","yyyy/MM/dd HH:mm:ss");
        } catch (Exception e) {
            date = dateFormat.parse(source, pos);
        }

        return date;
    }

    // 主要还是装饰这个方法
    @Override
    public Date parse(String source) throws ParseException {

        Date date = null;

        try {

            // 先按我的规则来
            date = DateUtils.parseDate(source,"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm","yyyy-MM-dd HH","yyyy-MM-dd","yyyy/MM/dd","yyyy/MM/dd HH","yyyy/MM/dd HH:mm","yyyy/MM/dd HH:mm:ss");
        } catch (Exception e) {
            date = dateFormat.parse(source);
        }

        return date;
    }

    // 这里装饰clone方法的原因是因为clone方法在jackson中也有用到
    @Override
    public Object clone() {
        Object format = dateFormat.clone();
        return new DateFormatConfig((DateFormat) format);
    }

}
