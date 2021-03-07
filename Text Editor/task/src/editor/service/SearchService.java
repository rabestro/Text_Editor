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
    private int index;

    public SearchService(final JTextArea textArea) {
        this.textArea = textArea;
        reset();
    }

    public void reset() {
        matchResultList = Collections.emptyList();
    }

    public void startSearch(final Pattern pattern) {
        final var matcher = pattern.matcher(textArea.getText());
        matchResultList = matcher.results().collect(Collectors.toUnmodifiableList());
        index = 0;
        selectText(index);
    }

    public void next() {
        if (matchResultList.isEmpty()) {
            return;
        }
        if (++index == matchResultList.size()) {
            index = 0;
        }
        selectText(index);
    }

    public void previous() {
        if (matchResultList.isEmpty()) {
            return;
        }
        if (--index < 0) {
            index = matchResultList.size() - 1;
        }
        selectText(index);
    }

    private void selectText(final int index) {
        selectText(matchResultList.get(index));
    }

    private void selectText(final MatchResult result) {
        textArea.setCaretPosition(result.end());
        textArea.select(result.start(), result.end());
        textArea.grabFocus();
    }
}
