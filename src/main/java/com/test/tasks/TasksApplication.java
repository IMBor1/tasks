package com.test.tasks;

import com.test.tasks.javaCore.MyTimeClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class TasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
		MyTimeClass myTimeClass = new MyTimeClass();
		myTimeClass.myTime(LocalDateTime.now());
	}

}
