package com.test.tasks.javaCore;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class MyTimeClass {
    public MyTimeClass() {
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy:MM:dd HH:mm:ss.SSS" )
    public void myTime(LocalDateTime localDateTime) {
        System.out.println(localDateTime);
    }
}
