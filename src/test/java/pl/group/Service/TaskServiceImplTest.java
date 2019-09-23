package pl.group.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.group.Entity.Task;
import pl.group.Repository.FileHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {

    @Mock
    private FileHandler fileHandler;

    @InjectMocks
    private TaskServiceImpl tested;


    @Test
    public void testAddTask_actualTaskExist_noSaveTask() throws IOException {
        //given
        Task task = new Task("actual");
        task.setStartTime(123442223L);
        given(fileHandler.getAllTasks()).willReturn(getTasks());

        //when
        tested.addTask(task);

        //then
        verify(fileHandler, times(0)).saveTask(any());
    }

    @Test
    public void testAddTask_notActualTaskExist_saveTask() throws IOException {
        //given
        Task task = new Task("notActual");
        task.setStartTime(1234422L);
        task.setStopTime(12344222L);
        given(fileHandler.getAllTasks()).willReturn(getTasks());

        //when
        tested.addTask(task);

        //then
        verify(fileHandler, times(1)).saveTask(task);
    }

    @Test
    public void testAddTask_taskThatIsNotExist_saveTask() throws IOException {
        //given
        Task task = new Task("noExistTask");
        task.setStartTime(123442223L);
        given(fileHandler.getAllTasks()).willReturn(getTasks());

        //when
        tested.addTask(task);

        //then
        verify(fileHandler, times(1)).saveTask(task);
    }

    private List<Task> getTasks() {
        Task actualTask = new Task("actual");
        actualTask.setStartTime(123442223L);
        Task notActualTask = new Task("notActual");
        notActualTask.setStartTime(1234422L);
        notActualTask.setStopTime(12344222L);
        return Arrays.asList(actualTask, notActualTask);
    }
}