package editor.component;

import editor.events.Command;
import editor.events.CommandEvent;
import editor.events.CommandListener;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.util.function.BiConsumer;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class AppToolbar extends JPanel {
    private static final Logger log = Logger.getLogger(AppToolbar.class.getName());

    private final JTextField textPattern = new JTextField(15);
    private final JCheckBox useRegex = new JCheckBox("Use regex");

    {
        textPattern.setName("SearchField");
        useRegex.setName("UseRegExCheckbox");
    }

    public AppToolbar(final CommandListener listener) {
        final var openButton = createButton("OpenButton", "OpenButton.gif");
        final var saveButton = createButton("SaveButton", "SaveButton.gif");
        final var searchButton = createButton("StartSearchButton", "StartSearchButton.gif");
        final var previousButton = createButton("PreviousMatchButton", "PreviousMatchButton.gif");
        final var nextButton = createButton("NextMatchButton", "NextMatchButton.gif");

        final BiConsumer<JButton, Command> addButton = (button, command) -> {
            button.addActionListener(event ->
                    listener.commandEventOccurred(new CommandEvent(this, command)));
            add(button);
        };

        setLayout(new FlowLayout(FlowLayout.LEFT));
        addButton.accept(openButton, Command.OPEN);
        addButton.accept(saveButton, Command.SAVE);
        add(textPattern);
        addButton.accept(searchButton, Command.START_SEARCH);
        addButton.accept(previousButton, Command.PREVIOUS);
        addButton.accept(nextButton, Command.NEXT);
        add(useRegex);
    }

    private JButton createButton(final String name, final String path) {
        final var url = "src/editor/images/" + path;
        final var button = new JButton();
        button.setName(name);
        button.setMargin(new Insets(0, 0, 0, 0));
        if (url == null) {
            log.severe("Unable to load image: " + path);
            button.setText(name);
        } else {
            button.setIcon(new ImageIcon(url, name));
        }
        return button;
    }

    public Pattern getPattern() {
        return Pattern.compile(textPattern.getText(), useRegex.isSelected() ? 0 : Pattern.LITERAL);
    }

    public void useRegex() {
        log.info("Use regular expressions");
        useRegex.setSelected(!useRegex.isSelected());
    }
}
