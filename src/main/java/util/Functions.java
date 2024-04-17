package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Note;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Functions {
    private static String trouble;

    ObjectMapper mapper = new ObjectMapper();

    public void add(Note obj){
        try {
            mapper.writeValue(new File("Note.json"), obj);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void read(int id){}
    public void readAll(){}
    public void edit(int id){}
    public void delete(int id){}

    private static boolean checkDate(String elem){
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yy ");
            LocalDate.parse(elem, dtf);
        }catch (DateTimeParseException e) {
            trouble = "неверно указана введенная дата: " + elem;
            return false;
        }
        return true;
    }
    public static String getTrouble() {
        return trouble;
    }
}
