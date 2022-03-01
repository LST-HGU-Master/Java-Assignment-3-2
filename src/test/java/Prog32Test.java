import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import java.io.*;

public class Prog32Test {

    @Test
    public void testEndThreeTimes()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        in.inputln("2");
        in.inputln("2");
        in.inputln("2");
        Prog32.main(null);

        // assertion
        String[] prints = bos.toString().split("\n");
        assertEquals(4, prints.length);
        assertTrue(prints[prints.length - 1].contains("プログラムを終了します"));

        // undo the binding in System
        System.setOut(originalOut);
    }

    @Test
    public void testCase1()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        in.inputln("1");
        in.inputln("1");
        in.inputln("1");
        Prog32.main(null);

        // assertion
        String[] prints = bos.toString().split("\n");
        assertTrue(prints[prints.length - 2].contains("グー"));
        assertFalse(prints[prints.length - 2].contains("チョキ"));
        assertFalse(prints[prints.length - 2].contains("パー"));

        // undo the binding in System
        System.setOut(originalOut);
    }

    @Test
    public void testCase2()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        in.inputln("2");
        in.inputln("2");
        in.inputln("2");
        Prog32.main(null);

        // assertion
        String[] prints = bos.toString().split("\n");
        assertTrue(prints[prints.length - 2].contains("チョキ"));
        assertFalse(prints[prints.length - 2].contains("グー"));
        assertFalse(prints[prints.length - 2].contains("パー"));

        // undo the binding in System
        System.setOut(originalOut);
    }

    @Test
    public void testCase3()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        in.inputln("3");
        in.inputln("3");
        in.inputln("3");
        Prog32.main(null);

        // assertion
        String[] prints = bos.toString().split("\n");
        assertTrue(prints[prints.length - 2].contains("パー"));
        assertFalse(prints[prints.length - 2].contains("グー"));
        assertFalse(prints[prints.length - 2].contains("チョキ"));

        // undo the binding in System
        System.setOut(originalOut);
    }

    @Test
    public void testDefault()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        in.inputln("0");
        in.inputln("0");
        in.inputln("0");
        Prog32.main(null);

        // assertion
        String[] prints = bos.toString().split("\n");
        assertTrue(prints[prints.length - 2].contains("不適切な入力です"));
        assertFalse(prints[prints.length - 2].contains("グー"));
        assertFalse(prints[prints.length - 2].contains("チョキ"));
        assertFalse(prints[prints.length - 2].contains("パー"));

        // undo the binding in System
        System.setOut(originalOut);
    }
}
