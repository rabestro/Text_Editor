package editor.service;

import javax.swing.JTextArea;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchService {
    private final JTextArea textArea;
    private List<MatchResult> matchResultList;
    private ListIterator<MatchResult> iterator;

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
        iterator = matchResultList.listIterator();
        next();
    }

    public void next() {
        if (matchResultList.isEmpty()) {
            return;
        }
        if (!iterator.hasNext()) {
            iterator = matchResultList.listIterator();
        }
        selectText(iterator.next());
    }

    public void previous() {
        if (matchResultList.isEmpty()) {
            return;
        }
        if (!iterator.hasPrevious()) {
            iterator = matchResultList.listIterator(matchResultList.size());
        }
        selectText(iterator.previous());
    }

    private void selectText(final MatchResult result) {
        textArea.setCaretPosition(result.end());
        textArea.select(result.start(), result.end());
        textArea.grabFocus();
    }
}
