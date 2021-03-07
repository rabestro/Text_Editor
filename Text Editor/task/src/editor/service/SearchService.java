package editor.service;

import javax.swing.JTextArea;
import java.util.Collections;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchService {
    private final JTextArea textArea;
    private List<MatchResult> matchResultList;

    public SearchService(final JTextArea textArea) {
        this.textArea = textArea;
        matchResultList = Collections.emptyList();
    }

    public int startSearch(final Pattern pattern) {
        final var matcher = pattern.matcher(textArea.getText());
        matchResultList = matcher.results().collect(Collectors.toUnmodifiableList());
        final var found = matchResultList.size();
        if (found > 0) {
            selectText(0);
        }
        return found;
    }
    private void selectText(final int index) {
        final var result = matchResultList.get(index);
        textArea.setCaretPosition(result.end());
        textArea.select(result.start(), result.end());
        textArea.grabFocus();
    }
}
