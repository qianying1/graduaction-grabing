package cn.grad.grabing.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils extends BaseLogger{

    public static Date parse(String date){
        try{
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        }catch (Exception e){
            log.error("fail to parse Date type from string type: "+date,e);
            return null;
        }
    }
}
