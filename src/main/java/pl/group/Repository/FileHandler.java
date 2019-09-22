package pl.group.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import pl.group.Entity.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class FileHandler {

    private final File file = ResourceUtils.getFile("/home/paulina/workspace/task-time-counter/src/main/resources/tasks.json");

    public FileHandler() throws FileNotFoundException {}


    public void save(JSONObject object) throws IOException {
        try (FileWriter fileWriter = new FileWriter(file.getPath())) {
            fileWriter.write(object.toString());
        }
    }

    public List<Task> getAllTasks() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(file, Task[].class));
    }
}
