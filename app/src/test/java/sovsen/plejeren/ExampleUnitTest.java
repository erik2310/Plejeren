package sovsen.plejeren;

import android.app.Instrumentation;
import android.widget.Button;

import org.junit.Test;

import sovsen.plejeren.view.WorkplanActivity;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void svar1_isCorrect() throws Exception {
        int svarA = 2;
        assertEquals(svarA, 2);
    }


}
