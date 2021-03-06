package editor;

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

        add(new Toolbar(textArea::getText, textArea::setText), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

}
