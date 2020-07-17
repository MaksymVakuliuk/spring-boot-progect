package com.spring.boot.project.demo.service;

public interface DbInitializer<T> {
    void initializeDb(String dataFilePath);
}
