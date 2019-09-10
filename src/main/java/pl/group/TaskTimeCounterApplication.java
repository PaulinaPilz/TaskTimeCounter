package pl.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.group.Service.TaskServiceImpl;

@SpringBootApplication
public class TaskTimeCounterApplication implements CommandLineRunner {

	@Autowired
	private TaskServiceImpl service;

	public static void main(String[] args) {
		SpringApplication.run(TaskTimeCounterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		service.isExistTask("task1");
	}
}
