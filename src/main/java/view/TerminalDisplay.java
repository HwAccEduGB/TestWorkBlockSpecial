package view;

public class TerminalDisplay {

    public void displayTerminalMenu() {
        System.out.println("""
                для добавления заметки нажмите 1
                для просмотра заметки нажмите 2
                для просмотра всех заметок нажмите 3
                для редактирования заметки нажмите 4
                для удаления заметки нажмите 5
                для выхода из программы нажмите 6""");
    }

    public void displayTerminalMessage(String message) {
        System.out.println(message);
    }
}
