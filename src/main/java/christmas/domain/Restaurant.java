package christmas.domain;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuCategory;
import java.util.Arrays;
import java.util.List;

public class Restaurant {

    public boolean isPresentation(int totalPrice) {
        return totalPrice >= 120000;
    }

    public boolean isChristmasDiscount(int date) {
        return date < 26;
    }

    public boolean isWeekend(int date) {
        for (Integer weekendDate : Date.WEEKEND.getDates()) {
            if (weekendDate.equals(date)) {
                return true;
            }
        }

        return false;
    }

    public boolean isWeekday(int date) {
        for (Integer weekendDate : Date.WEEKDAY.getDates()) {
            if (weekendDate.equals(date)) {
                return true;
            }
        }

        return false;
    }

    public boolean isStar(int date) {
        for (Integer starDate : Date.STAR.getDates()) {
            if (starDate.equals(date)) {
                return true;
            }
        }

        return false;
    }

    public int christmasDiscount(int date) {
        int discount = 1000;

        discount += 100 * (date - 1);

        return discount;
    }

    public int weekdayDiscount(Buyer buyer) {
        List<MenuAndCount> menuAndCounts = buyer.getMenuAndCounts();

        int count = menuAndCounts.stream()
                .filter(mac -> Arrays.stream(Menu.values())
                        .anyMatch(menu -> menu.getCategory().equals(MenuCategory.DESSERT)
                                && menu.getName().contains(mac.getMenuName())))
                .mapToInt(MenuAndCount::getCount)
                .sum();

        return 2023 * count;
    }

    public int weekendDiscount(Buyer buyer) {
        List<MenuAndCount> menuAndCounts = buyer.getMenuAndCounts();

        int count = menuAndCounts.stream()
                .filter(mac -> Arrays.stream(Menu.values())
                        .anyMatch(menu -> menu.getCategory().equals(MenuCategory.MAIN)
                                && menu.getName().contains(mac.getMenuName())))
                .mapToInt(MenuAndCount::getCount)
                .sum();

        return 2023 * count;
    }

    public int starDiscount() {
        return 1000;
    }

    public int presentationDiscount() {
        return 25000;
    }

    public String eventBadge(int calculateTotalDiscount) {
        if (calculateTotalDiscount >= 20000) {
            return "산타";
        }

        if (calculateTotalDiscount >= 10000) {
            return "트리";
        }

        if (calculateTotalDiscount >= 5000) {
            return "별";
        }

        return "없음";
    }
}
