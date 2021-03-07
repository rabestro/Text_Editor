package editor.component;

import editor.events.Command;
import editor.events.CommandEvent;
import editor.events.CommandListener;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.util.function.BiConsumer;
import java.util.logging.Logger;

public class AppToolbar extends JPanel {
    private static final Logger log = Logger.getLogger(AppToolbar.class.getName());

    //    private final MenuListener menuListener;
//    private final JTextField fileName = new JTextField(15);
    private final JButton openButton = createButton("OpenButton", "Open24.gif");
    private final JButton saveButton = createButton("SaveButton", "Save24.gif");
    private final JButton searchButton = createButton("StartSearchButton", "Search24.gif");
    private final JButton previousButton = createButton("PreviousMatchButton", "Back24.gif");
    private final JButton nextButton = createButton("NextMatchButton", "Forward24.gif");
    private final JTextField searchText = new JTextField(15);
    private final JCheckBox useRegex = new JCheckBox("Use regex");

    {
//        fileName.setName("FilenameField");
        searchText.setName("SearchField");
        useRegex.setName("UseRegExCheckbox");
    }


    public AppToolbar(final CommandListener listener) {
        final BiConsumer<JButton, Command> setCommand = (button, command) ->
                button.addActionListener(event ->
                        listener.commandEventOccurred(new CommandEvent(this, command)));

        setCommand.accept(openButton, Command.OPEN);
        setCommand.accept(saveButton, Command.SAVE);
        setCommand.accept(searchButton, Command.START_SEARCH);
        setCommand.accept(previousButton, Command.PREVIOUS);
        setCommand.accept(nextButton, Command.NEXT);

        setLayout(new FlowLayout(FlowLayout.LEFT));
//        add(fileName);
        add(openButton);
        add(saveButton);
        add(searchText);
        add(searchButton);
        add(previousButton);
        add(nextButton);
        add(useRegex);
    }

//    public Path getFile() {
//        return Path.of(fileName.getText());
//    }

    private JButton createButton(final String name, final String path) {
//        final var url = getClass().getResource(path);
        // Text Editor/task/
        final var url = "src/editor/images/" + path;
        final var button = new JButton();
        button.setName(name);
        if (url == null) {
            log.severe("Unable to load image: " + path);
            button.setText(name);
        } else {
            button.setIcon(new ImageIcon(url, name));
        }
        return button;
    }
}
