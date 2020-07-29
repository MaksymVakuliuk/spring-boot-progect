package com.spring.boot.project.demo.service;

public interface DbService {
    void initializeDb(String dataFilePath);

    void clearDb();
}
