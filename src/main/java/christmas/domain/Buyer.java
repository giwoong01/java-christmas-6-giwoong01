package christmas.domain;

public class Buyer {
    private final MenuAndCounts menuAndCounts;

    public Buyer(MenuAndCounts menuAndCounts) {
        this.menuAndCounts = menuAndCounts;
    }

    public int getTotalPrice() {
        return menuAndCounts.calculateTotalPrice();
    }

    @Override
    public String toString() {
        return menuAndCounts.toString();
    }
}
