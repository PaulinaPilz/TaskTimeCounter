package pl.group.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import pl.group.Entity.Task;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FileHandler {

    private File file;

    private List<Task> tasks;

    public FileHandler() throws IOException {
        this.file = new File("tasks.json");
        this.tasks = laodFile();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void saveToFile(Task task) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            if (raf.length() == 0) {
                JSONArray ja = new JSONArray(new Object[] { task});
                raf.writeBytes(ja.toString());
                return;
            }
            JSONObject jo = new JSONObject(task);
            raf.seek(raf.length() - 1);
            raf.writeBytes("," + jo.toString() + "]");
        }
    }

    public List<Task> laodFile() throws IOException {
        List<Task> tasks = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            if (raf.length() != 0) {
                ObjectMapper mapper = new ObjectMapper();
                return Arrays.asList(mapper.readValue(file, Task[].class));
            }
        }
        return tasks;
    }
}
