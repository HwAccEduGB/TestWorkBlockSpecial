package present;

import model.Note;
import util.Functions;
import view.TerminalDisplay;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Present {
    private int menuItem;
    private String titleNote;
    private String bodyNote;

    Note note;
    TerminalDisplay display;
    Functions functions;


    public Present() {
        this.display = new TerminalDisplay();
        this.functions = new Functions();
//        this.note = new Note();
    }

    public void run() {

        Scanner scanner = new Scanner(System.in);
        display.displayTerminalMenu();
        menuItem = scanner.nextInt();
        switch (menuItem) {
            case (1):
                display.displayTerminalMessage("Введите заголовок заметки");
                titleNote = scanner.next();
                display.displayTerminalMessage("Введите заметку");
                bodyNote = scanner.next();
                functions.add(new Note(generateRandomID(), titleNote, bodyNote, getCurrentDate()));
                break;
            case (2):
                functions.read(setID());
                break;
            case (3):
                functions.readAll();
                break;
            case (4):
                functions.edit(setID());
                break;
            case (5):
                functions.delete(setID());
                break;
            case (6):
                System.exit(0);
            default:
                display.displayTerminalMessage("Введена неверная команда");
                break;
        }
        scanner.close();
    }

    private int setID() {
        Scanner scanner = new Scanner(System.in);
        display.displayTerminalMessage("Введите ID заметки");
        return scanner.nextInt();
    }

    private static String getCurrentDate() {
        return LocalDate.now().toString();
    }

    private int generateRandomID() {
        return ThreadLocalRandom.current().nextInt(1, 2147483646 + 1);
    }
}
