package editor;

import editor.component.Toolbar;
import editor.service.LoadText;
import editor.service.SaveText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
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

        final var toolbar = new Toolbar(textArea::getText, textArea::setText);
        add(new Toolbar(textArea::getText, textArea::setText), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        final var loadText = new LoadText(toolbar::getFile, textArea::setText);
        final var saveText = new SaveText(toolbar::getFile, textArea::getText);

        final var menuBar = new JMenuBar();
        final var menu = new JMenu("File");
        menu.setName("MenuFile");
        menu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(menu);
        final var menuItemLoad = new JMenuItem("Load", KeyEvent.VK_L);
        menuItemLoad.setName("MenuLoad");
        menuItemLoad.addActionListener(loadText);
        final var menuItemSave = new JMenuItem("Save", KeyEvent.VK_S);
        menuItemSave.setName("MenuSave");
        menuItemSave.addActionListener(saveText);
        final var menuItemExit = new JMenuItem("Exit", KeyEvent.VK_X);
        menuItemExit.setName("MenuExit");
        menuItemExit.addActionListener(actionEvent -> System.exit(0));
        menu.add(menuItemLoad);
        menu.add(menuItemSave);
        menu.add(menuItemExit);
        setJMenuBar(menuBar);
        setVisible(true);
    }

}
