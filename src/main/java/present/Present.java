package present;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    Scanner scanner;
    Note note;
    JParser<Note> parser;

    public Present() {
        this.display = new TerminalDisplay();
        this.functions = new Functions();
        this.mapper = new ObjectMapper();
        this.notes = new ArrayList<>();
        this.note = new Note();
        this.parser = new JParser<>();
        this.scanner = new Scanner(System.in);
    }

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
            case (1) -> {
                display.displayTerminalMessage("Введите заголовок заметки");
                scanner.nextLine();
                String titleNote = scanner.nextLine();
                display.displayTerminalMessage("Введите заметку");
                String bodyNote = scanner.nextLine();
                notes.add(new Note(functions.generateRandomID(), titleNote, bodyNote, functions.getCurrentDate()));
                functions.addData(file, notes);
                continuable();
            }
            case (2) -> {
                if (checkFileForEmpty(file)) {
                    for (Note elem : notes) {
                        System.out.println(elem);
                    }
                    display.displayTerminalMessage(functions.read(getID(), notes));
                }
                continuable();
            }
            case (3) -> {
                if (checkFileForEmpty(file)) {
                    display.displayTerminalMessage(functions.readAll(notes));
                }
                continuable();
            }
            case (4) -> {
                if (checkFileForEmpty(file)) {
                    String tempID = getID();
                    display.displayTerminalMessage("Введите новый текст заметки");
                    scanner.nextLine();
                    String tempNoteText = scanner.nextLine();
                    display.displayTerminalMessage(functions.edit(tempID, notes, tempNoteText, file));
                }
                continuable();
            }
            case (5) -> {
                if (checkFileForEmpty(file)) {
                    display.displayTerminalMessage(functions.delete(getID(), notes, file));
                }
                continuable();
            }
            case (6) -> {
                scanner.close();
                System.exit(0);
            }
            default -> {
                display.displayTerminalMessage("Введена неверная команда");
                continuable();
            }
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
