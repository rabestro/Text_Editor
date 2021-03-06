package editor.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.logging.Logger;

public class AppToolbar extends JPanel {
    private static final Logger log = Logger.getLogger(AppToolbar.class.getName());

    private final JTextField fileName = new JTextField(15);
    private final JButton openButton = new JButton(new ImageIcon("open.svg"));
    private final JButton saveButton = new JButton("Save");

    {
        fileName.setName("FilenameField");
        openButton.setName("LoadButton");
        saveButton.setName("SaveButton");
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(fileName);
        add(openButton);
        add(saveButton);
    }


    public AppToolbar(final ActionListener loadText, final ActionListener saveText) {
        openButton.addActionListener(loadText);
        saveButton.addActionListener(saveText);

    }

    public Path getFile() {
        return Path.of(fileName.getText());
    }


}
