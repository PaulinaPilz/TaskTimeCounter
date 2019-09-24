package pl.group.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.group.Entity.Task;
import pl.group.Repository.FileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
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

    @Captor
    private ArgumentCaptor<ArrayList<Task>> captor;


    @Test
    public void testAddTask_actualTaskExist_noSaveTask() throws IOException {
        //given
        Task newTask = new Task("actual");
        newTask.setStartTime(1236662225L);
        Task oldTask = new Task("actual");
        oldTask.setStartTime(123442223L);
        given(fileHandler.getActiveTask()).willReturn(oldTask);

        //when
        tested.addTask(newTask);

        //then
        verify(fileHandler, times(0)).saveToFile(any());
    }

    @Test
    public void testAddTask_notActualTaskExist_saveTask() throws IOException {
        //given
        Task task = new Task("notActual");
        task.setStartTime(1234422L);
        task.setStopTime(12344222L);
        given(fileHandler.getTasks()).willReturn(getTasks());

        //when
        tested.addTask(task);

        //then
        verify(fileHandler, times(1)).saveToFile(task);
    }

    @Test
    public void testAddTask_taskThatIsNotExist_saveTask() throws IOException {
        //given
        Task task = new Task("noExistTask");
        task.setStartTime(123442223L);
        given(fileHandler.getTasks()).willReturn(getTasks());

        //when
        tested.addTask(task);

        //then
        verify(fileHandler, times(1)).saveToFile(task);
    }

    @Test
    public void testAddTask_newTaskAndActiveStatusOtherTask_changeStatusOtherTask() {
        //given
        Task task = new Task("newTask");
        task.setStartTime(123445555L);
        given(fileHandler.getTasks()).willReturn(getTasks());

        //when
        tested.addTask(task);

        //then
        verify(fileHandler, times(1)).updateTasks(captor.capture());
        assertEquals(false, captor.getValue().get(0).isActive());
    }

    private List<Task> getTasks() {
        Task actualTask = new Task("actual");
        actualTask.setStartTime(123442223L);
        Task notActualTask = new Task("notActual");
        notActualTask.setStartTime(1234422L);
        notActualTask.setStopTime(12344222L);
        notActualTask.setActive(false);
        return Arrays.asList(actualTask, notActualTask);
    }
}