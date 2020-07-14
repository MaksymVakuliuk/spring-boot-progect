package com.spring.boot.project.demo.unit;

import java.util.List;

public interface Parser {
    List<?> parse(List<String> reviews);
}
