package com.spring.boot.project.demo.unit;

import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.util.*;

@Component
public class ParseCSV {

//    public void parse(Reader reader) throws IOException {
//        Iterable<CSVRecord> records = CSVFormat.DEFAULT
//                .withFirstRecordAsHeader()
//                .parse(reader);
//
//        for (CSVRecord record : records) {
//            System.out.println(record.get(0));
//            System.out.println(record.get(2));
//        }
//    }

//    @SneakyThrows
//    @Override
//    public List<String> parse(Reader reader) {
//        Iterable<CSVRecord> records = CSVFormat.DEFAULT
//                .withFirstRecordAsHeader()
//                .parse(reader);
//        for (CSVRecord record : records) {
//            List
//        }
//        return null;
//    }

    @SneakyThrows
    public List<Map<String, String>> parseToMap(Reader reader) {
        CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        List<String> headers = parser.getHeaderNames();
        List<CSVRecord> records = parser.getRecords();
        List<Map<String, String>> items = new ArrayList<>();
        for (CSVRecord record : records) {
            Map<String, String> item = new TreeMap<>();
            for (String header : headers) {
                item.put(header, record.get(header));
            }
            items.add(item);
        }
        return items;
    }
}
