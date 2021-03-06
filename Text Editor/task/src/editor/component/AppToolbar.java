package editor.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.logging.Logger;

public class AppToolbar extends JPanel {
    private static final Logger log = Logger.getLogger(AppToolbar.class.getName());

    private final JTextField fileName = new JTextField(15);
    private final JButton openButton = createButton("OpenButton", "Open24.gif");
    private final JButton saveButton = createButton("SaveButton", "Save24.gif");
    private final JButton searchButton = createButton("StartSearchButton", "Search24.gif");
    private final JButton previousMatchButtonButton = createButton("PreviousMatchButton", "Back24.gif");
    private final JButton nextMatchButton = createButton("NextMatchButton", "Forward24.gif");
    private final JTextField searchText = new JTextField(15);

    {
        fileName.setName("FilenameField");
        searchText.setName("SearchField");

        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(fileName);
        add(openButton);
        add(saveButton);
        add(searchText);
        add(searchButton);
        add(previousMatchButtonButton);
        add(nextMatchButton);
    }


    public AppToolbar(final ActionListener loadText, final ActionListener saveText) {
        openButton.addActionListener(loadText);
        saveButton.addActionListener(saveText);

    }

    public Path getFile() {
        return Path.of(fileName.getText());
    }

    private JButton createButton(final String name, final String path) {
//        final var url = getClass().getResource(path);
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
