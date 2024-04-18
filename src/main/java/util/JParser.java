package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Note;

import java.io.*;
import java.util.List;

public class JParser<T> {
    ObjectMapper mapper = new ObjectMapper();

    public List<Note> readJson(String fileName) {
        try {
            return mapper.readValue(new File(fileName), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
