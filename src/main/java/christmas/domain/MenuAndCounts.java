package christmas.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MenuAndCounts {
    private final List<MenuAndCount> menuAndCounts;
    private int totalCount;

    public MenuAndCounts(List<MenuAndCount> menuAndCounts) {
        validateDuplicateMenu();
        this.menuAndCounts = menuAndCounts;
        this.totalCount = menuAndCounts.stream()
                .mapToInt(MenuAndCount::getCount)
                .sum();
    }

    private void validateDuplicateMenu() {
        Set<String> duplicateMenu = new HashSet<>();
        for (MenuAndCount menuAndCount : menuAndCounts) {
            if (!duplicateMenu.add(menuAndCount.getMenuName())) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    public int calculateTotalPrice() {
        return menuAndCounts.stream()
                .mapToInt(this::calculateMenuPrice)
                .sum();
    }

    private int calculateMenuPrice(MenuAndCount menuAndCount) {
        String menuName = menuAndCount.getMenuName();
        int count = menuAndCount.getCount();

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
                .map(MenuAndCount::toString)
                .collect(Collectors.joining("\n"));
    }

}
