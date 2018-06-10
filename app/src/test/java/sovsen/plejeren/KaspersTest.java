package sovsen.plejeren;

import org.junit.Test;

import sovsen.plejeren.view.WorkplanActivity;

import static org.junit.Assert.assertEquals;

public class KaspersTest {

    @Test
    public  void test(){

        WorkplanActivity test = new WorkplanActivity();
        System.out.println(test.getClass().getName());

        //Tester om navnet på den "skrevne" info (klassenavn) om klassen passer til klassenavnet.
        assertEquals("sovsen.plejeren.view.WorkplanActivity", test.getClass().getName());


    }
}
