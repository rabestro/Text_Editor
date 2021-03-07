package editor.service;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileSystemView;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

import static java.nio.file.StandardOpenOption.*;

public class FileService extends JFileChooser {
    private static final Logger log = Logger.getLogger(FileService.class.getName());

    private final JFileChooser jfc;
    private final JTextArea textArea;

    public FileService(final JTextArea textArea) {
        this.textArea = textArea;
        jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        setName("FileChooser");
    }

    public void open() {
        log.info("Open a document");
        if (jfc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        final var selectedFile = jfc.getSelectedFile();
        log.info(selectedFile.getAbsolutePath());
        final var filePath = Path.of(selectedFile.toURI());

        try {
            textArea.setText(Files.readString(filePath));
        } catch (IOException e) {
            textArea.setText("");
            log.warning(e::getMessage);
        }
    }

    public void save() {
        log.info("Save a document");
        if (jfc.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        final var selectedFile = jfc.getSelectedFile();
        log.info(selectedFile.getAbsolutePath());
        final var filePath = Path.of(selectedFile.toURI());
        try {
            Files.writeString(filePath, textArea.getText(), CREATE, WRITE, TRUNCATE_EXISTING);
        } catch (IOException e) {
            log.warning(e::getMessage);
        }
    }

}
