package com.zehao.Chaozhou.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;


// s：source 资源（页面传递过来的信息）
// t：target 目标（转成 Date）
public class DateConverter implements Converter<String, Date>{

    @Override
    public Date convert(String source) {

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(source);
            /*Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(source);*/
            System.out.println(date);
            return date;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}