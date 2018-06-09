package sovsen.plejeren;

import org.junit.Test;

import sovsen.plejeren.view.presenter.Task;

import static org.junit.Assert.assertEquals;

public class EriksTest {

    // Laver et task objekt
    Task task = new Task("Rengøring");

    @Test
    public void testTask() {

        // Tester om Rengøring bliver hentet som String fra task objektet
        assertEquals("Rengøring", task.getTaskName());

    }

}
