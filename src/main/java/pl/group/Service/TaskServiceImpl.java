package pl.group.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.group.Entity.Task;
import pl.group.Repository.FileHandler;

import java.io.IOException;
import java.util.List;
import java.util.function.LongSupplier;


@Service
public class TaskServiceImpl implements TaskService {


    @Autowired
    private FileHandler fileHandler;

    private LongSupplier actualTime = System::currentTimeMillis;


    public boolean isExistActualTask(String pTask) {
        long currentTime = actualTime.getAsLong();
        List<Task> tasks = null;
        try {
            tasks = fileHandler.getAllTasks();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Task task : tasks) {
            if (pTask.equals(task.getName()) && currentTime <= task.getStartTime() && task.getStopTime() == null) {
                return true;
            }
        }
        return false;
    }
}
