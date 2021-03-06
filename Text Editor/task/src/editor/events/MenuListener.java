package editor.events;

import java.util.EventListener;

public interface MenuListener extends EventListener {
    void menuEventOccurred(MenuEvent menuEvent);
}
