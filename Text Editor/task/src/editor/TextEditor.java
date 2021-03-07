package editor;

import editor.component.AppMenu;
import editor.component.AppToolbar;
import editor.events.CommandEvent;
import editor.service.SearchService;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileSystemView;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

import static java.nio.file.StandardOpenOption.*;

public class TextEditor extends JFrame {
    private static final Logger log = Logger.getLogger(TextEditor.class.getName());

    private final JFileChooser jfc;
    private final JTextArea textArea = new JTextArea();
    private final SearchService searchService = new SearchService(textArea);
    private final AppToolbar toolbar = new AppToolbar(this::processCommand);
    {
        jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setName("FileChooser");
        this.add(jfc);

        textArea.setName("TextArea");
        textArea.setBounds(0, 0, 300, 300);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

    }


    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setTitle("The text editor");

        final JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setName("ScrollPane");
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(250, 250));
        add(scrollPane, BorderLayout.CENTER);

        add(scrollPane, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);

        setJMenuBar(new AppMenu(this::processCommand));
        setVisible(true);
    }

    private void processCommand(final CommandEvent event) {
        File selectedFile;
        Path filePath;

        switch (event.getCommand()) {
            case EXIT:
                this.dispose();
                return;
            case OPEN:
                log.info("Open a document");
                int returnValue = jfc.showOpenDialog(this);
                if (returnValue != JFileChooser.APPROVE_OPTION) {
                    return;
                }
                selectedFile = jfc.getSelectedFile();
                log.info(selectedFile.getAbsolutePath());
                filePath = Path.of(selectedFile.toURI());

                try {
                    textArea.setText(Files.readString(filePath));
                } catch (IOException e) {
                    textArea.setText("");
                    log.warning(e::getMessage);
                }
                searchService.reset();
                return;
            case SAVE:
                log.info("Save a document");
                if (jfc.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
                    return;
                }
                selectedFile = jfc.getSelectedFile();
                log.info(selectedFile.getAbsolutePath());
                filePath = Path.of(selectedFile.toURI());
                try {
                    Files.writeString(filePath, textArea.getText(), CREATE, WRITE, TRUNCATE_EXISTING);
                } catch (IOException e) {
                    log.warning(e::getMessage);
                }
                return;
            case START_SEARCH:
                log.info("Start search");
                searchService.startSearch(toolbar.getPattern());
                return;
            case PREVIOUS:
                log.info("Previous match");
                searchService.previous();
                return;
            case NEXT:
                log.info("Next match");
                searchService.next();
                return;
            case USE_REGEX:
                log.info("Use regular expressions");
                return;
            default:
                log.info("Unimplemented action occurs");
        }
    }

}
