package com.spring.boot.project.demo.unit;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class FileUtil implements FileReader {
    @Override
    @SneakyThrows
    public List<String> readLines(String path) {
        Path filePath = Paths.get(path);
        return Files.readAllLines(filePath);
    }

    @SneakyThrows
    @Override
    public Reader getReader(String path) {
        Path filePath = Paths.get(path);
        return Files.newBufferedReader(filePath);
    }
}
