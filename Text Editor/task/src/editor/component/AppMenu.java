package editor.component;

import editor.events.Command;
import editor.events.MenuEvent;
import editor.events.MenuListener;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class AppMenu extends JMenuBar {

    private final MenuListener menuListener;

    public AppMenu(final MenuListener listener) {

        this.menuListener = listener;
        final var menuFile = new JMenu("File");
        menuFile.setName("MenuFile");
        menuFile.setMnemonic(KeyEvent.VK_F);
        add(menuFile);

        final var menuItemOpen = new JMenuItem("Open", KeyEvent.VK_L);
        menuItemOpen.setName("MenuLoad");
        menuItemOpen.addActionListener(getListener(Command.OPEN));

        final var menuItemSave = new JMenuItem("Save", KeyEvent.VK_S);
        menuItemSave.setName("MenuSave");
        menuItemSave.addActionListener(getListener(Command.SAVE));

        final var menuItemExit = new JMenuItem("Exit", KeyEvent.VK_X);
        menuItemExit.setName("MenuExit");
        menuItemExit.addActionListener(getListener(Command.EXIT));

        menuFile.add(menuItemOpen);
        menuFile.add(menuItemSave);
        menuFile.add(menuItemExit);

    }

    private ActionListener getListener(final Command command) {
        return a -> menuListener.menuEventOccurred(new MenuEvent(this, command));
    }
}
