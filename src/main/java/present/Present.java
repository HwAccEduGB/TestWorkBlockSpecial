package present;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import model.Note;
import util.Functions;
import util.JParser;
import view.TerminalDisplay;

import java.io.*;
import java.util.*;

public class Present {
    ObjectMapper mapper;
    TerminalDisplay display;
    Functions functions;
    List<Note> notes;
    //    Map<String, String> mapNotes;
    Scanner scanner;
    Note note;
    JParser<Note> parser;

    public Present() {
        this.display = new TerminalDisplay();
        this.functions = new Functions();
        this.mapper = new ObjectMapper();
        this.notes = new ArrayList<>();
//        this.mapNotes = new HashMap<>();
        this.note = new Note();
        this.parser = new JParser<>();
        this.scanner = new Scanner(System.in);
    }

//    public void test() throws IOException {
//
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("note.json"));
//            String line = reader.readLine();
////            while (line != null) {
////                System.out.println(line);
////                line = reader.readLine();
////            }
//            reader.close();
//            System.out.println(line);
//            Gson gson = new Gson();
//            note = gson.fromJson(line, Note.class);
////            Note note = mapper.readValue(line, Note.class);
//            System.out.println("DataInfo: " + note.getID() + " " + note.getTitleNote() + " " + note.getBodyNote());
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }


    public void run() {
        display.displayTerminalMessage("Добро пожаловать в программу ЗАМЕТКИ");
        display.displayTerminalMessage("Введите имя файла с которым вы хотите работать");
        String currentFile = scanner.next();
        File file = new File(currentFile + ".json");
        if (!file.exists()) {
            display.displayTerminalMessage("файл c именем: " + currentFile + " не обнаружен\n" +
                    "хотите создать новый файл? Y/N");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("y")) {
                display.displayTerminalMessage("введите имя нового файла");
                String newFileName = scanner.next();
                File newFile = new File(newFileName + ".json");
                try {
                    if (newFile.createNewFile()) {
                        display.displayTerminalMessage("файл c именем: " + newFile + " создан");
                        run();
                    } else {
                        display.displayTerminalMessage("файл c именем: " + newFile + " уже существует");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (choice.equalsIgnoreCase("n")) {
                scanner.close();
                System.exit(0);
            }
        }

        notes = parser.readJson(String.valueOf(file));

        display.displayTerminalMenu();
        int menuItem = scanner.nextInt();
        switch (menuItem) {
            case (1):
                display.displayTerminalMessage("Введите заголовок заметки");
                String titleNote = scanner.next();
                display.displayTerminalMessage("Введите заметку");
                String bodyNote = scanner.next();
                notes.add(new Note(functions.generateRandomID(), titleNote, bodyNote, functions.getCurrentDate()));
                functions.addData(file, notes);
                continuable();
                break;
            case (2):
                if (checkFileForEmpty(file)) {
                    for (Note elem : notes) {
                        System.out.println(elem);
                    }
                    display.displayTerminalMessage(functions.read(getID(), notes));

                }
                continuable();
                break;
            case (3):
//                display.displayTerminalMessage(String.valueOf(mapNotes));
                for (int i = 0; i < notes.size(); i++) {
                    display.displayTerminalMessage(String.valueOf(notes.get(i)));
                }
                continuable();
                break;
            case (4):
                if (checkFileForEmpty(file)) {
                    functions.edit(getID());
                }

                continuable();
                break;
            case (5):
                if (checkFileForEmpty(file)) {
                    display.displayTerminalMessage(functions.delete(getID(), notes, file));
                }

                continuable();
                break;
            case (6):
                scanner.close();
                System.exit(0);
            default:
                display.displayTerminalMessage("Введена неверная команда");
                continuable();
                break;
        }
    }

    private String getID() {
        display.displayTerminalMessage("Введите ID заметки");
        return scanner.next();
    }

    private void continuable() {
        display.displayTerminalMessage("Хотите продолжить? Y/N");
        String wantContinue = scanner.next();
        if (wantContinue.equalsIgnoreCase("y")) {
            run();
        } else if (wantContinue.equalsIgnoreCase("n")) {
            scanner.close();
            System.exit(0);
        }
    }

    private boolean checkFileForEmpty(File file) {
        if (file.length() == 0) {
            display.displayTerminalMessage("Файл пуст");
            return false;
        }
        return true;
    }
}
