package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Note;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Functions {

    private static String trouble;

    ObjectMapper mapper = new ObjectMapper();

    public void addData(File file, List<Note> list){
        try {
            mapper.writeValue(file, list);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void read(String id){

    }

    public String readAll(File file){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String s;
            while ((s=br.readLine())!=null){
                sb.append(s);
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return String.valueOf(sb);
    }

    public void edit(String id){}
    public void delete(String id){}

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
    public String getCurrentDate() {
        return LocalDate.now().toString();
    }
    public int generateRandomID() {
        return ThreadLocalRandom.current().nextInt(1, 2147483646 + 1);
    }
}
