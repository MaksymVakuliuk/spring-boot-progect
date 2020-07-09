package com.spring.boot.project.demo.unit;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.Reader;

import static org.junit.Assert.*;

public class ParseCSVTest {
    private final ParseCSV parseCSV = new ParseCSV();
    private final Reader reader = new FileUtil()
            .getBufferReader("/home/max5akul/MateAcademy/spring-boot/src/test/resources"
                    + "/tests/fileUtilTest/lines-for-test.csv");

    @SneakyThrows
    @Test
    public void parse() {
        parseCSV.parseToMap(reader).stream().forEach(System.out::println);
    }
}