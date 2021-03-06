package editor;

import editor.component.AppMenu;
import editor.component.AppTextArea;
import editor.component.Toolbar;
import editor.service.LoadText;
import editor.service.SaveText;

import javax.swing.*;
import java.awt.*;
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

    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setTitle("The second stage");

        final var pane = new AppTextArea();
        add(pane, BorderLayout.CENTER);

        final var toolbar = new Toolbar(pane, pane);
        add(toolbar, BorderLayout.NORTH);

        final var loadText = new LoadText(toolbar::getFile, pane);
        final var saveText = new SaveText(toolbar::getFile, pane);

        setJMenuBar(new AppMenu(loadText, saveText));
        setVisible(true);
    }

}
