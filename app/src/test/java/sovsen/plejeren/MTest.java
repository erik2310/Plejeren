package sovsen.plejeren;

import sovsen.plejeren.view.presenter.Client;

import static org.junit.Assert.assertEquals;


public class MTest {

    Client client = new Client("Mads", "Kværredevej", "12.00");

    @org.junit.Test
    public void TestClient() {

        // Tester om navn bliver hentet som String fra task objektet
        assertEquals("Mads", client.getmName());

    }


}
