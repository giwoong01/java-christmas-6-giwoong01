package christmas.domain;

import java.text.NumberFormat;

public enum Menu {
    APPETIZER_MUSHROOM_SOUP(6_000),
    APPETIZER_TAPAS(5_500),
    APPETIZER_CAESAR_SALAD(8_000),

    MAIN_T_BONE_STEAK(55_000),
    MAIN_BARBECUE_RIB(54_000),
    MAIN_SEA_FOOD_PASTA(35_000),
    MAIN_CHRISTMAS_PASTA(25_000),

    DESSERT_CHOCOLATE_CAKE(15_000),
    DESSERT_ICE_CREAM(5_000),

    DRINK_ZERO_COLA(3_000),
    DRINK_RED_WINE(60_000),
    DRINK_CHAMPAGNE(25_000);

    private final int price;

    Menu(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        return numberFormat.format(price);
    }
}
