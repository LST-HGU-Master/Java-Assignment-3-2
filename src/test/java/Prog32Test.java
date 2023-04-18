import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
/**
 * @version (20230417)
 *   supporting both println and print("\n") on Windows
 **/
public class Prog32Test {
    InputStream originalIn;
    PrintStream originalOut;
    ByteArrayOutputStream bos;
    StandardInputStream in;

    @BeforeEach
    void before() {
        //back up binding
        originalIn  = System.in;
        originalOut = System.out;
        //modify binding
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        in = new StandardInputStream();
        System.setIn(in);
    }

    @AfterEach
    void after() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testEndThreeTimes()
    {
        // action
        in.inputln("2");
        in.inputln("2");
        in.inputln("2");
        in.inputln("2"); // this is for avoiding exception(error) on unit test
        in.inputln("2"); // this is for avoiding exception(error) on unit test
        Prog32.main(null);

        // assertion
        String[] prints = bos.toString().split("\r\n|\n");
        try {
            assertEquals(4, prints.length, "繰り返し回数が指定以外です!");
            assertTrue(prints[prints.length - 1].contains("プログラムを終了します"),
                "【最優先】「プログラムを終了します」の一文が無い、又は文字が完全一致しません!"
            );
            assertFalse(prints[prints.length - 2].contains("パー"));
        } catch (AssertionError err) {
            after();
            throw err;
        }
    }

    @Test
    public void testCase1()
    {
        // action
        in.inputln("1");
        in.inputln("1");
        in.inputln("1");
        Prog32.main(null);

        // assertion
        String[] prints = bos.toString().split("\r\n|\n");
        try {
            assertTrue(prints[prints.length - 2].contains("グー"));
            assertFalse(prints[prints.length - 2].contains("チョキ"));
            assertFalse(prints[prints.length - 2].contains("パー"));
        } catch (AssertionError e) {
            after();
            AssertionError asErr = new AssertionError("入力「1」に対して「グー」がprintされません!");
            throw asErr;
        }
    }

    @Test
    public void testCase2()
    {
        // action
        in.inputln("2");
        in.inputln("2");
        in.inputln("2");
        Prog32.main(null);

        // assertion
        String[] prints = bos.toString().split("\r\n|\n");
        try {
            assertTrue(prints[prints.length - 2].contains("チョキ"));
            assertFalse(prints[prints.length - 2].contains("グー"));
            assertFalse(prints[prints.length - 2].contains("パー"));
        } catch (AssertionError e) {
            after();
            AssertionError asErr = new AssertionError("入力「2」に対して「チョキ」がprintされません!");
            throw asErr;
        }
    }

    @Test
    public void testCase3()
    {
        // action
        in.inputln("3");
        in.inputln("3");
        in.inputln("3");
        Prog32.main(null);

        // assertion
        String[] prints = bos.toString().split("\r\n|\n");
        try {
            assertTrue(prints[prints.length - 2].contains("パー"));
            assertFalse(prints[prints.length - 2].contains("グー"));
            assertFalse(prints[prints.length - 2].contains("チョキ"));
        } catch (AssertionError e) {
            after();
            AssertionError asErr = new AssertionError("入力「3」に対して「パー」がprintされません!");
            throw asErr;
        }
    }

    @Test
    public void testDefault()
    {
        // action
        in.inputln("0");
        in.inputln("0");
        in.inputln("0");
        Prog32.main(null);

        // assertion
        String[] prints = bos.toString().split("\r\n|\n");
        try {
            assertTrue(prints[prints.length - 2].contains("不適切な入力です"));
            assertFalse(prints[prints.length - 2].contains("グー"));
            assertFalse(prints[prints.length - 2].contains("チョキ"));
            assertFalse(prints[prints.length - 2].contains("パー"));
        } catch (AssertionError e) {
            after();
            AssertionError asErr = new AssertionError("1,2,3以外の入力に対して「不適切な入力です」がprintされません!");
            throw asErr;
        }
    }
}
