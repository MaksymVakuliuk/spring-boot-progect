package com.spring.boot.project.demo.unit;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class FileUtilTest {
    private final FileReader fileReader = new FileUtil();
    private static final String LINES_TEST_FILE_PATH = "/home/max5akul/MateAcademy/test/lines-for-test.txt";
    private static final String EMPTY_TEST_FILE_PATH = "/home/max5akul/MateAcademy/test/empty-file-test.txt";

    @Test
    public void readLines() {
        String expected = "[I have 4 lines., first line, second line, third line, fourth line.]";
        assertEquals(expected, fileReader.readLines(LINES_TEST_FILE_PATH).toString());
    }

    @Test
    public void readLinesOfEmptyFile() {
        assertTrue(fileReader.readLines(EMPTY_TEST_FILE_PATH).isEmpty());
    }
}
