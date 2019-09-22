package pl.group.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.ResourceUtils;
import pl.group.Entity.Task;
import pl.group.Repository.FileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.LongSupplier;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {

    @Mock
    private FileHandler fileHandler;

    @Mock
    private LongSupplier timeSupplier;

    @InjectMocks
    private TaskServiceImpl tested;


    @Test
    public void testIsExistActualTask_actualTaskExist_true() throws IOException {
        //given
        given(timeSupplier.getAsLong()).willReturn(123442223L);
        given(fileHandler.getAllTasks()).willReturn(getTasks());

        //when
        boolean result = tested.isExistActualTask("actual");

        //then
        assertEquals(true, result);
    }

    @Test
    public void testIsExistActualTask_notActualTaskExist_false() throws IOException {
        //given
        given(timeSupplier.getAsLong()).willReturn(1234422L);
        given(fileHandler.getAllTasks()).willReturn(getTasks());

        //when
        boolean result = tested.isExistActualTask("notActual");

        //then
        assertEquals(false, result);
    }

    @Test
    public void testIsExistActualTask_taskThatIsNot_false() throws IOException {
        //given
        given(timeSupplier.getAsLong()).willReturn(123442223L);
        given(fileHandler.getAllTasks()).willReturn(getTasks());

        //when
        boolean result = tested.isExistActualTask("noTask");

        //then
        assertEquals(false, result);
    }

    private List<Task> getTasks() {
        Task actualTask = new Task("actual", 123442223L, null);
        Task notActualTask = new Task("notActual", 1234422L, 12344222L);
        return Arrays.asList(actualTask, notActualTask);
    }
}