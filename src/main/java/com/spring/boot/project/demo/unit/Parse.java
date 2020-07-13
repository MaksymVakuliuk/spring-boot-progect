package com.spring.boot.project.demo.unit;

import java.io.Reader;
import java.util.List;

public interface Parse<T> {
    List<T> parse(Reader reader);
}
