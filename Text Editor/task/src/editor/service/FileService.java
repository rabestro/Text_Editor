package editor.service;

import editor.component.TextPane;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

import static java.nio.file.StandardOpenOption.*;

public class FileService extends JFileChooser {
    private static final Logger log = Logger.getLogger(FileService.class.getName());

    private final TextPane textPane;

    public FileService(final TextPane textPane) {
        super(FileSystemView.getFileSystemView().getHomeDirectory());
        this.textPane = textPane;
        setName("FileChooser");
        setMultiSelectionEnabled(false);
        setFileSelectionMode(JFileChooser.FILES_ONLY);
    }

    public void open() {
        log.entering(getName(), "open", "Open a document");
        final int result = showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }
        log.info(getSelectedFile().getAbsolutePath());
        final var filePath = Path.of(getSelectedFile().toURI());

        try {
            textPane.setText(Files.readString(filePath));
        } catch (IOException e) {
            textPane.setText("");
            log.warning(e::getMessage);
        }
    }

    public void save() {
        log.info("Save a document");
        if (showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        log.info(getSelectedFile().getAbsolutePath());
        final var filePath = Path.of(getSelectedFile().toURI());
        try {
            Files.writeString(filePath, textPane.getText(), CREATE, WRITE, TRUNCATE_EXISTING);
        } catch (IOException e) {
            log.warning(e::getMessage);
        }
    }

}
