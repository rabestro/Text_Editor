package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

public class TextEditor extends JFrame {
    private static final Logger log = Logger.getLogger(TextEditor.class.getName());

    private final JTextArea textArea = new JTextArea();

    {
        textArea.setName("TextArea");
        textArea.setBounds(0, 0, 300, 300);
        textArea.setFont(new Font("Serif", Font.ITALIC, 24));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }

    private final JScrollPane scrollPane = new JScrollPane(textArea);

    {
        scrollPane.setName("ScrollPane");
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(250, 250));
    }

    private final JTextField fileName = new JTextField(15);

    {
        fileName.setName("FilenameField");
    }

    private final JButton loadButton = new JButton("Load");

    {
        loadButton.setName("LoadButton");
        loadButton.addActionListener(this::loadFile);
    }

    private final JButton saveButton = new JButton("Save");

    {
        saveButton.setName("SaveButton");
        saveButton.addActionListener(this::saveFile);
    }

    private final JPanel topMenu = new JPanel();

    {
        topMenu.setLayout(new FlowLayout());
        topMenu.add(fileName);
        topMenu.add(saveButton);
        topMenu.add(loadButton);
    }

    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setTitle("The second stage");

        add(topMenu, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void saveFile(final ActionEvent actionEvent) {
        try {
            final var filePath = Path.of(fileName.getText());
            Files.writeString(filePath, textArea.getText(), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            log.warning(e::getMessage);
        }
    }

    private void loadFile(final ActionEvent actionEvent) {
        try {
            final var filePath = Path.of(fileName.getText());
            textArea.setText(Files.readString(filePath));
            Files.readString(filePath);
        } catch (IOException e) {
            textArea.setText("");
            log.warning(e::getMessage);
        }

    }

}
