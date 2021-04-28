package org.personal.springbootbase.util;

import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class DateTimeUtil {

    public ZonedDateTime now(){
        return ZonedDateTime.now();
    }
}
