package editor.events;

import java.util.EventObject;

public class MenuEvent extends EventObject {

    private Command command;

    public MenuEvent(Object source) {
        super(source);
    }

    public MenuEvent(Object source, Command command) {
        super(source);
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
