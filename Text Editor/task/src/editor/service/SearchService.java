package editor.service;

import editor.component.TextPane;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchService {
    private static final Logger log = Logger.getLogger(SearchService.class.getName());

    private final TextPane textArea;
    private List<MatchResult> matchResultList;
    private int index;

    public SearchService(final TextPane textArea) {
        this.textArea = textArea;
        reset();
    }

    public void reset() {
        matchResultList = Collections.emptyList();
    }

    public void startSearch(final Pattern pattern) {
        log.info("Start search");
        final var matcher = pattern.matcher(textArea.getText());
        matchResultList = matcher.results().collect(Collectors.toUnmodifiableList());
        index = -1;
        next();
    }

    public void next() {
        log.info("Next match");
        if (matchResultList.isEmpty()) {
            return;
        }
        if (++index == matchResultList.size()) {
            index = 0;
        }
        selectText(index);
    }

    public void previous() {
        log.info("Previous match");
        if (matchResultList.isEmpty()) {
            return;
        }
        if (--index < 0) {
            index = matchResultList.size() - 1;
        }
        selectText(index);
    }

    private void selectText(final int index) {
        final var result = matchResultList.get(index);
        textArea.highlightMatch(result.start(), result.end());
    }

}
