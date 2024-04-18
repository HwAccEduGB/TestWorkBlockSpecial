package view;

import model.Note;

public class TerminalDisplay {

    public void displayTerminalMenu() {
        System.out.println("для добавления заметки нажмите 1\n" +
                "для просмотра заметки нажмите 2\n" +
                "для просмотра всех заметок нажмите 3\n" +
                "для редактирования заметки нажмите 4\n" +
                "для удаления заметки нажмите 5\n" +
                "для выхода из программы нажмите 6");
    }

    public void displayTerminalMessage(String message) {
        System.out.println(message);
    }
    public void displayTerminalMessage(Note note) {
        System.out.println(note);
    }
}
