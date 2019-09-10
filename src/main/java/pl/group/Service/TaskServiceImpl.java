package pl.group.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.group.Entity.Task;

import java.io.IOException;
import java.util.List;
import java.util.function.LongSupplier;


@Service
public class TaskServiceImpl implements TaskService {


    @Autowired
    private FileHandler fileHandler;

    private LongSupplier timeSupplier = System::currentTimeMillis;


    public boolean isExistTask(String pTask) {
        long currentTime = timeSupplier.getAsLong();
        List<Task> tasks = null;
        try {
            tasks = fileHandler.parseTask();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Task task : tasks) {
            if (pTask.equals(task.getName()) && currentTime < task.getStartTime()) {
                return true;
            }
        }
        return false;
    }
}
