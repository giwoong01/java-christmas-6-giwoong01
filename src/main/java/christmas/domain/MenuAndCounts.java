package christmas.domain;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuCategory;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MenuAndCounts {
    private final List<MenuAndCount> menuAndCounts;
    private final int totalCount;

    public MenuAndCounts(List<MenuAndCount> menuAndCounts) {
        this.menuAndCounts = menuAndCounts;
        validateIncludesFoodOrder();

        validateDuplicateMenu();
        this.totalCount = menuAndCounts.stream()
                .mapToInt(MenuAndCount::getCount)
                .sum();
        validateMaxMenuOrderCount();
    }

    private void validateIncludesFoodOrder() {
        boolean hasFoodOrder = menuAndCounts.stream()
                .anyMatch(menuAndCount -> Arrays.stream(Menu.values())
                        .filter(menu -> menu.getName().equals(menuAndCount.getMenuName()))
                        .anyMatch(menu -> menu.getCategory() != MenuCategory.DRINK));

        if (!hasFoodOrder) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문할 수 없습니다.");
        }
    }

    private void validateDuplicateMenu() {
        Set<String> duplicateMenu = new HashSet<>();
        for (MenuAndCount menuAndCount : menuAndCounts) {
            if (!duplicateMenu.add(menuAndCount.getMenuName())) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    private void validateMaxMenuOrderCount() {
        if (totalCount > 20) {
            throw new IllegalArgumentException("[ERROR] 메뉴는 한 번에 20개까지만 주문 가능합니다.");
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

        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(menuName))
                .findFirst()
                .map(menu -> menu.getPrice() * count)
                .orElse(0);
    }

    @Override
    public String toString() {
        return menuAndCounts.stream()
                .map(MenuAndCount::toString)
                .collect(Collectors.joining("\n"));
    }

}
