package com.spring.boot.project.demo.unit;

import java.util.List;

public interface Parser<T> {
    List<T> parse(List<String> reviews);
}
