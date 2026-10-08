import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
/**
 * @version (20230417)
 *     supporting both println and print("\n") on Windows
 * @version (20261008) 
 *     1) revised  testEndThreeTimes for println miss, 2) using output.contains(...)  
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
        String output = bos.toString().replace("\r\n", "\n");
        String[] prints = output.split("\n");
        if (prints.length == 7) {
            fail("入力指示文「数値を入力してください: 」の出力に println（改行あり）を使っていませんか？ print（改行なし）を使用してください。");
        }
        
        assertEquals(4, prints.length, 
            "出力の行数が想定と異なります! 繰り返し回数が3回になっているか、また入力指示文の末尾で改行（println）していないか確認してください。"
        );

        assertTrue(output.contains("プログラムを終了します"),
            "「プログラムを終了します」の一文がない、または文字が完全一致しません!"
        );
    }

    @Test
    public void testCase1() {
        in.inputln("1");
        in.inputln("1");
        in.inputln("1");
        Prog32.main(null);
        
        String output = bos.toString();

        assertTrue(output.contains("グー"), "入力「1」に対して「グー」が表示されていません!");
        assertFalse(output.contains("チョキ"), "入力「1」に対して不要な「チョキ」が出力されています!");
        assertFalse(output.contains("パー"), "入力「1」に対して不要な「パー」が出力されています!");
    }

    @Test
    public void testCase2()  {
        // action
        in.inputln("2");
        in.inputln("2");
        in.inputln("2");
        Prog32.main(null);

         String output = bos.toString();

        assertTrue(output.contains("チョキ"), "入力「2」に対して「チョキ」が表示されていません!");
        assertFalse(output.contains("グー"), "入力「2」に対して不要な「グー」が出力されています!");
        assertFalse(output.contains("パー"), "入力「2」に対して不要な「パー」が出力されています!");
    }

    @Test
    public void testCase3()
    {
        // action
        in.inputln("3");
        in.inputln("3");
        in.inputln("3");
        Prog32.main(null);

        String output = bos.toString();

        assertTrue(output.contains("パー"), "入力「3」に対して「パー」が表示されていません!");
        assertFalse(output.contains("グー"), "入力「3」に対して不要な「グー」が出力されています!");
        assertFalse(output.contains("チョキ"), "入力「3」に対して不要な「チョキ」が出力されています!");
    }

    @Test
    public void testDefault()
    {
        // action
        in.inputln("0");
        in.inputln("0");
        in.inputln("0");
        Prog32.main(null);

        String output = bos.toString();

        assertTrue(output.contains("不適切な入力です"), "1,2,3 以外の入力に対して「不適切な入力です」が表示されていません!");
        assertFalse(output.contains("グー"), "範囲外の入力に対して「グー」が出力されています!");
        assertFalse(output.contains("チョキ"), "範囲外の入力に対して「チョキ」が出力されています!");
        assertFalse(output.contains("パー"), "範囲外の入力に対して「パー」が出力されています!");
    }
}
