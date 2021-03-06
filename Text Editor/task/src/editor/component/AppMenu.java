package editor.component;

import editor.events.Command;
import editor.events.CommandEvent;
import editor.events.CommandListener;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class AppMenu extends JMenuBar {

    private final CommandListener commandListener;

    public AppMenu(final CommandListener listener) {

        this.commandListener = listener;
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
        return a -> commandListener.commandEventOccurred(new CommandEvent(this, command));
    }
}
