package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Logger;

import static java.nio.file.StandardOpenOption.*;

public class Toolbar extends JPanel implements ActionListener {
    private static final Logger log = Logger.getLogger(Toolbar.class.getName());

    private final Supplier<String> textSupplier;
    private final Consumer<String> textConsumer;
    private final JTextField fileName = new JTextField(15);
    private final JButton loadButton = new JButton("Load");
    private final JButton saveButton = new JButton("Save");

    {
        fileName.setName("FilenameField");
        loadButton.setName("LoadButton");
        loadButton.addActionListener(this);
        saveButton.setName("SaveButton");
        saveButton.addActionListener(this);
    }

    Toolbar(final Supplier<String> textSupplier, final Consumer<String> textConsumer) {
        this.textSupplier = textSupplier;
        this.textConsumer = textConsumer;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(fileName);
        add(loadButton);
        add(saveButton);
    }

    public String getFileName() {
        return fileName.getText();
    }

    public Path getFile() {
        return Path.of(fileName.getText());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        final var filePath = Path.of(fileName.getText());

        try {
            if (event.getSource().equals(loadButton)) {
                textConsumer.accept(Files.readString(filePath));
            } else {
                Files.writeString(filePath, textSupplier.get(), CREATE, WRITE, TRUNCATE_EXISTING);
            }
        } catch (IOException e) {
            log.warning(e::getMessage);
        }

    }
}
