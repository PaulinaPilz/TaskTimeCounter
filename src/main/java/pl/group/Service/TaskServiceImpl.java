package pl.group.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.group.Entity.Task;
import pl.group.Repository.FileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger LOG = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private FileHandler fileHandler;

    @Override
    public void addTask(Task pTask) {
        try {
            if (isExistActualTask(pTask)) {
                LOG.info("Task: [" + pTask.getName() + "] already exists");
                return;
            }
            changeTaskStatusWhenIsActive();
            fileHandler.saveToFile(pTask);
        } catch (IOException e) {
            LOG.error("Error while reading the file");
        }
    }

    private boolean isExistActualTask(Task pTask) {
        for (Task task : fileHandler.getTasks()) {
            if (pTask.getName().equals(task.getName()) && pTask.getStartTime() <= task.getStartTime() && task.getStopTime() == null) {
                return true;
            }
        }
        return false;
    }

    private void changeTaskStatusWhenIsActive() {
        List<Task> tasks = new ArrayList<>();
        for (Task task : fileHandler.getTasks()) {
            if (task.isActive()) {
                task.setActive(false);
            }
            tasks.add(task);
        }
        fileHandler.setTasks(tasks);
    }
}
