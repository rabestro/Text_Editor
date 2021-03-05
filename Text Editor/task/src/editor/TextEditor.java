package editor;

import javax.swing.*;

public class TextEditor extends JFrame {
    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setTitle("The first stage");

        final var textArea = new JTextArea();
        textArea.setName("TextArea");
        textArea.setBounds(0, 0, 300, 300);
        add(textArea);

        setVisible(true);
        setLayout(null);
    }
}
