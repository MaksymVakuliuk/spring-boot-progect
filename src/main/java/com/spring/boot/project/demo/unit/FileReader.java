package com.spring.boot.project.demo.unit;

import java.util.List;

public interface FileReader {
    List<String> readLines(String path);
}
