package sovsen.plejeren;


import org.junit.Test;

import sovsen.plejeren.view.presenter.Task;

import static org.junit.Assert.assertEquals;

public class NielsTest {

    // Laver et task objekt
    Task task = new Task("Vask");

    @Test
    public void testTask() {

        // Tester om Vask bliver hentet som String fra task objektet
        assertEquals("Vask", task.getTaskName());

    }
}
