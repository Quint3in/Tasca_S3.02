package org.example;

import java.util.ArrayList;
import java.util.List;

public class Undo {

    private static final Undo instance = new Undo();
    private final List<String> commands;

    private Undo() {
        commands = new ArrayList<>();
    }
    public static Undo getInstance() {
        return instance;
    }
    public void addCommand(String command) {
        commands.add(command);
    }
    public String undoCommand() {
        return commands.removeLast();
    }
    public void showHistory() {
        commands.forEach(System.out::println);
    }
}
