package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Note;
import net.bytebuddy.description.method.MethodDescription;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Functions {
    ObjectMapper mapper = new ObjectMapper();

    public void addData(File file, List<Note> list) {
        try {
            mapper.writeValue(file, list);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String read(String id, List<Note> notes) {
        for (Note elem : notes) {
            if (elem.getID().equals(id)) {
                return "данные по запрошенному ID: " + "\nЗаголовок заметки: " + elem.getTitleNote() +
                        "\nТело заметки: " + elem.getBodyNote() + "\nДата заметки: " + elem.getDate();
            }
        }
        return "Данный ID не найден";
    }

    public void edit(String id) {
    }

    public String delete(String id, List<Note> notes, File file) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getID().equals(id)) {
                notes.remove(i);
                addData(file, notes);
                return "Заметка удалена";
            }
        }
        return "Данный ID не найден";
    }

    public String getCurrentDate() {
        return LocalDate.now().toString();
    }

    public String generateRandomID() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(1, 10000));
    }
}
