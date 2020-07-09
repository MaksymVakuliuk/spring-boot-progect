package com.spring.boot.project.demo.unit;

import java.io.BufferedReader;
import java.util.List;

public interface FileReader {
    List<String> readLines(String path);

    BufferedReader getBufferReader(String path);
}
