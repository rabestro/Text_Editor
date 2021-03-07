package editor;

import editor.component.AppMenu;
import editor.component.AppToolbar;
import editor.events.CommandEvent;
import editor.service.FileService;
import editor.service.SearchService;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.util.logging.Logger;

public class TextEditor extends JFrame {
    private static final Logger log = Logger.getLogger(TextEditor.class.getName());

    private final JTextArea textArea = new JTextArea();
    private final SearchService searchService = new SearchService(textArea);
    private final FileService fileService = new FileService(textArea);
    private final AppToolbar toolbar = new AppToolbar(this::processCommand);

    {
        add(fileService);
        textArea.setName("TextArea");
        textArea.setBounds(0, 0, 300, 300);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }

    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setTitle("The Text Editor");

        final JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setName("ScrollPane");
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);
        setJMenuBar(new AppMenu(this::processCommand));
        setVisible(true);
    }

    private void processCommand(final CommandEvent event) {
        switch (event.getCommand()) {
            case EXIT:
                this.dispose();
                return;
            case OPEN:
                fileService.open();
                searchService.reset();
                return;
            case SAVE:
                fileService.save();
                return;
            case START_SEARCH:
                searchService.startSearch(toolbar.getPattern());
                return;
            case PREVIOUS:
                searchService.previous();
                return;
            case NEXT:
                searchService.next();
                return;
            case USE_REGEX:
                toolbar.useRegex();
                return;
            default:
                log.info("Unimplemented action occurs");
        }
    }

}
