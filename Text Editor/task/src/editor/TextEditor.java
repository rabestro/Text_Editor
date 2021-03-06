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

    private final JTextField fileName = new JTextField(15);

    {
        fileName.setName("FilenameField");
        // fileName.addActionListener(e -> log.info("file name action listener"));
    }

    private final JButton loadButton = new JButton("Load");

    {
        loadButton.setName("LoadButton");
        loadButton.addActionListener(action -> {
            textArea.setText("Load from file...");
        });
    }

    private final JButton saveButton = new JButton("Save");

    {
        saveButton.setName("SaveButton");
        saveButton.addActionListener(action -> {
            textArea.setText("Save to file...");
        });
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
        setSize(300, 300);
        setTitle("The second stage");

        add(topMenu, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

}
