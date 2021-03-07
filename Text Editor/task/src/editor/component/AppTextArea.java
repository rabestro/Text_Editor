package editor.component;

import javax.swing.*;
import java.awt.*;

public class AppTextArea extends JScrollPane {
    private final JTextArea textArea;

    public AppTextArea(JTextArea textArea) {
        super(textArea);
        this.textArea = textArea;
        textArea.setName("TextArea");
        textArea.setBounds(0, 0, 300, 300);
        textArea.setFont(new Font("Serif", Font.ITALIC, 24));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        setName("ScrollPane");
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setPreferredSize(new Dimension(250, 250));
        add(textArea);
    }

}
