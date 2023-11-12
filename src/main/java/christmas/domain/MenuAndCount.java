package christmas.domain;

import christmas.domain.menu.Menu;
import java.util.Arrays;

public class MenuAndCount {
    private final String menuName;
    private final int count;

    public MenuAndCount(String menuName, String count) {
        validateNotExistsMenu(menuName);
        this.menuName = menuName;

        validateCountIsNumber(count);
        validateCountIsPositive(Integer.parseInt(count));
        this.count = Integer.parseInt(count);
    }

    private void validateNotExistsMenu(String menuName) {
        boolean isExists = Arrays.stream(Menu.values())
                .anyMatch(menu -> menu.getName().equals(menuName));

        if (!isExists) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateCountIsPositive(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateCountIsNumber(String count) {
        try {
            Integer.parseInt(count);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public String getMenuName() {
        return menuName;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return String.format("%s %d개", menuName, count);
    }
}
