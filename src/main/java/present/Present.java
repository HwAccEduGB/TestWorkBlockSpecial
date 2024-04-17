package present;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Note;
import util.Functions;
import view.TerminalDisplay;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Present {
    ObjectMapper mapper;
    TerminalDisplay display;
    Functions functions;
    List<Note> notes;
    Scanner scanner = new Scanner(System.in);
    Note note;

    public Present() {
        this.display = new TerminalDisplay();
        this.functions = new Functions();
        this.mapper = new ObjectMapper();
        this.notes = new ArrayList<>();
        this.note = new Note();
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
                    functions.read(getID());
                }

                continuable();
                break;
            case (3):
                if (checkFileForEmpty(file)) {
                    display.displayTerminalMessage(functions.readAll(file));
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
                    functions.delete(getID());
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
        Scanner scanner = new Scanner(System.in);
        display.displayTerminalMessage("Введите ID заметки");
        return scanner.nextLine();
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
