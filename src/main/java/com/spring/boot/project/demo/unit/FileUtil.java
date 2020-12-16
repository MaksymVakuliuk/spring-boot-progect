package com.spring.boot.project.demo.unit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class FileUtil implements FileReader {
    @Override
    public List<String> readLines(String path) {
        Path filePath = Paths.get(path);
        try {
            return Files.readAllLines(filePath);
        } catch (IOException ioException) {
            throw new RuntimeException("Can't read string from file: ", ioException);
        }
    }
}
