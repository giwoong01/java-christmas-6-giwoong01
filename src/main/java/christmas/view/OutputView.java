package christmas.view;

import christmas.domain.Buyer;
import christmas.domain.Restaurant;
import java.text.NumberFormat;

public class OutputView {
    private final NumberFormat numberFormat;

    public OutputView() {
        numberFormat = NumberFormat.getInstance();
    }

    public void previewEventBenefitMessage(int inputDate) {
        System.out.printf("12월 %d일 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", inputDate);
    }

    public void orderMenuMessage(Buyer buyer) {
        System.out.println("\n<주문 메뉴>");
        System.out.println(buyer);
    }

    public void totalPriceBeforeDiscountMessage(Buyer buyer) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(numberFormat.format(buyer.getTotalPrice()) + "원");
    }

    public void presentedMenuMessage(boolean isPresentation) {
        System.out.println("\n<증정 메뉴>");
        String presentationsMenu = "없음";

        if (isPresentation) {
            presentationsMenu = "샴페인 1개";
        }
        System.out.println(presentationsMenu);
    }

    public void christmasDayDiscountMessage(int inputDate, Restaurant restaurant) {
        System.out.printf("크리스마스 디데이 할인: -%s원%n", numberFormat.format(restaurant.christmasDiscount(inputDate)));
    }

    public void weekdayOrWeekendDiscountMessage(String weekdayOrWeekendDiscount) {
        System.out.print(weekdayOrWeekendDiscount);
    }

    public void startDiscountMessage(Restaurant restaurant) {
        System.out.printf("특별 할인: -%s원%n", numberFormat.format(restaurant.starDiscount()));
    }

    public void presentationDiscountMessage(Restaurant restaurant) {
        System.out.printf("증정 이벤트: -%s원%n",
                numberFormat.format(restaurant.presentationDiscount()));
    }

    public void benefitsDetailsMessage() {
        System.out.println("\n<혜택 내역>");
    }

    public void totalBenefitAmount(int calculateTotalDiscount) {
        String totalBenefitAmount = String.format("%s원%n", calculateTotalDiscount);
        System.out.println("\n<총혜택 금액>");

        if (calculateTotalDiscount > 0) {
            totalBenefitAmount = String.format("-%s원%n", numberFormat.format(calculateTotalDiscount));
        }
        System.out.print(totalBenefitAmount);
    }

    public void discountAfterTotalPriceMessage(String discountAfterTotalPrice) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.print(discountAfterTotalPrice);
    }

    public void eventBadgeMessage(Restaurant restaurant, int calculateTotalDiscount) {
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(restaurant.eventBadge(calculateTotalDiscount));
    }
}
