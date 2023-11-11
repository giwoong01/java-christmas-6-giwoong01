package christmas.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Buyer {
    private final List<String> menuAndCounts;

    public Buyer(List<String> menuAndCounts) {
        this.menuAndCounts = menuAndCounts;
    }

    public int calculateTotalPrice() {
        return menuAndCounts.stream()
                .mapToInt(this::calculateMenuPrice)
                .sum();
    }

    private int calculateMenuPrice(String menuAndCount) {
        String[] split = menuAndCount.split("-");
        String menuName = split[0];
        int count = Integer.parseInt(split[1]);

        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuName)) {
                return menu.getPrice() * count;
            }
        }

        throw new IllegalArgumentException("Invalid menu name: " + menuName);
    }

    @Override
    public String toString() {
        return menuAndCounts.stream()
                .map(this::formatMenuAndCount)
                .collect(Collectors.joining("\n"));
    }

    // 변수 수정, 예외 처리
    private String formatMenuAndCount(String menuAndCount) {
        String[] split = menuAndCount.split("-");
        return String.format("%s %s개", split[0], split[1]);
    }
}
