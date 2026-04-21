package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UndoTest {

    private Undo undo;

    @BeforeEach
    void setUp() throws Exception {
        undo = Undo.getInstance();
        clearCommands();
    }

    @Test
    void single_instance_for_undo() {
        assertThat(undo).isSameAs(Undo.getInstance());
    }

    @Test
    void add_command_stores_command_in_history() throws Exception {
        undo.addCommand("mkdir carpeta");

        assertThat(getCommands()).containsExactly("mkdir carpeta");
    }

    @Test
    void undo_command_removes_and_returns_last_command() throws Exception {
        undo.addCommand("mkdir carpeta");
        undo.addCommand("cd carpeta");

        String removedCommand = undo.undoCommand();

        assertThat(removedCommand).isEqualTo("cd carpeta");
        assertThat(getCommands()).containsExactly("mkdir carpeta");
    }

    @Test
    void undo_command_throws_exception_when_history_is_empty() {
        assertThatThrownBy(() -> undo.undoCommand())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void show_history_prints_commands_in_insertion_order() {
        undo.addCommand("mkdir carpeta");
        undo.addCommand("cd carpeta");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(output));

            undo.showHistory();
        } finally {
            System.setOut(originalOut);
        }

        assertThat(normalize(output.toString()))
                .isEqualTo("mkdir carpeta\ncd carpeta");
    }

    @SuppressWarnings("unchecked")
    private List<String> getCommands() throws Exception {
        Field commandsField = Undo.class.getDeclaredField("commands");
        commandsField.setAccessible(true);
        return (List<String>) commandsField.get(undo);
    }

    private void clearCommands() throws Exception {
        getCommands().clear();
    }

    private String normalize(String text) {
        return text.replace("\r\n", "\n").trim();
    }
}
