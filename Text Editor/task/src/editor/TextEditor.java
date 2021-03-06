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

    private final JScrollPane areaScrollPane = new JScrollPane(textArea);

    {
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(250, 250));
    }

    private final JTextField fileName = new JTextField(15);

    {
        // fileName.addActionListener(e -> log.info("file name action listener"));
    }

    private final JButton loadButton = new JButton("Load");

    {
        loadButton.addActionListener(action -> {
            textArea.setText("Load from file...");
        });
    }

    private final JPanel topMenu = new JPanel();

    {
        topMenu.setLayout(new FlowLayout());
        topMenu.add(fileName);
        topMenu.add(loadButton);
    }

    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setTitle("The first stage");
//        add(new JButton("North"), BorderLayout.NORTH);
//        add(new JButton("South"), BorderLayout.SOUTH);
//        add(new JButton("West"), BorderLayout.WEST);
//        add(new JButton("East"), BorderLayout.EAST);
//        add(new JButton("Center"), BorderLayout.CENTER);

        add(topMenu, BorderLayout.NORTH);
        add(areaScrollPane, BorderLayout.CENTER);

        setVisible(true);
//        setLayout(null);
    }

    private void loadText() {
        textArea.setText("Load from file...");
    }
}
