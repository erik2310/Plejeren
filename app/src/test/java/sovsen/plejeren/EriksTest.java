package sovsen.plejeren;

import android.app.Activity;
import android.widget.Button;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class EriksTest {

    // Deklarer en Button variabel
    private Button mLogin_button;

    Activity activity = new Activity();

    @Test
    public void testLoginButton() {

        // Sætter knappen med id login_button til mLogin_button variabel
        mLogin_button = (Button) activity.findViewById(R.id.login_button);

        // Tjekker om objektet ikke er null
        assertNotNull(mLogin_button);

    }

}
