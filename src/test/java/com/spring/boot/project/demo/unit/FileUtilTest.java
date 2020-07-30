package com.spring.boot.project.demo.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FileUtilTest {
    private static final String LINES_TEST_FILE_PATH
            = "src/test/resources/tests/util/lines-for-test.txt";
    private static final String LINES_TEST_CSV_FILE_PATH
            = "src/test/resources/tests/util/lines-for-test.csv";
    private static final String EMPTY_TEST_FILE_PATH
            = "src/test/resources/tests/util/empty-file-test.txt";
    private final FileUtil fileUtil = new FileUtil();

    @Test
    public void readLines() {
        String expectedTxt
                = "[I have 4 lines., first line, second line, third line, fourth line.]";
        assertEquals(expectedTxt, fileUtil.readLines(LINES_TEST_FILE_PATH).toString());
        String expectedCsv
                = "[first_row,second_row,third_row,fourth_road,"
                + " first_line,first_line,first_line,first_line,"
                + " second_line,second_line,second_line,second_line,"
                + " third_line,third_line,third_line,third_line,"
                + " fourth_line,fourth_line,fourth_line,fourth_line]";
        assertEquals(expectedCsv, fileUtil.readLines(LINES_TEST_CSV_FILE_PATH).toString());
    }

    @Test
    public void readLinesOfEmptyFile() {
        assertTrue(fileUtil.readLines(EMPTY_TEST_FILE_PATH).isEmpty());
    }
}
