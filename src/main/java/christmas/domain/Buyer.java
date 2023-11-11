package christmas.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Buyer {
    private final List<String> menuAndCounts;

    public Buyer(List<String> menuOfNumbers) {
        this.menuAndCounts = menuOfNumbers;
    }

    @Override
    public String toString() {
        return menuAndCounts.stream()
                .map(this::formatMenuAndCount)
                .collect(Collectors.joining("\n"));
    }

    private String formatMenuAndCount(String menuOfNumber) {
        String[] split = menuOfNumber.split("-");
        return String.format("%s %s개", split[0], split[1]);
    }
}
