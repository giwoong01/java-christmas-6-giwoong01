package christmas.controller;

import christmas.domain.Buyer;
import christmas.domain.Restaurant;
import christmas.view.InputView;
import java.text.NumberFormat;

public class GameMachine {
    private final NumberFormat numberFormat;
    private final Restaurant restaurant;
    private final InputView inputView;

    public GameMachine() {
        this.restaurant = new Restaurant();
        this.inputView = new InputView();
        numberFormat = NumberFormat.getInstance();
    }

    public void start() {
        // 구매자 입력
        int inputDate = inputView.inputVisitDate();

        Buyer buyer = inputView.inputMenuAndCounts();

        // 출력
        System.out.printf("12월 %d일 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", inputDate);

        System.out.println("\n<주문 메뉴>");
        System.out.println(buyer);

        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(numberFormat.format(buyer.calculateTotalPrice()) + "원");

        System.out.println("\n<증정 메뉴>");
        String presentationsMenu = "없음";
        boolean isPresentation = restaurant.isPresentation(buyer.calculateTotalPrice());
        if (isPresentation) {
            presentationsMenu = "샴페인 1개";
        }
        System.out.println(presentationsMenu);

        // 혜택 내역
        System.out.println("\n<혜택 내역>");
        boolean isDiscount = buyer.calculateTotalPrice() >= 10000;
        int calculateTotalDiscount = 0;
        // 크리스마스 디데이
        boolean isChristmasDiscount = restaurant.isChristmasDiscount(inputDate);
        if (isChristmasDiscount && isDiscount) {
            calculateTotalDiscount += restaurant.christmasDiscount(inputDate);
            System.out.printf("크리스마스 디데이 할인: -%s원%n",
                    numberFormat.format(restaurant.christmasDiscount(inputDate)));
        }

        // 평일 할인
        // 주말 할인
        String weekdayOrWeekendDiscount = "";
        boolean isWeekday = restaurant.isWeekday(inputDate);
        if (isWeekday && isDiscount) {
            calculateTotalDiscount += restaurant.weekdayDiscount();
            weekdayOrWeekendDiscount = String.format("평일 할인: -%s원%n",
                    numberFormat.format(restaurant.weekdayDiscount()));
        }

        boolean isWeekend = restaurant.isWeekend(inputDate);
        if (isWeekend && isDiscount) {
            calculateTotalDiscount += restaurant.weekendDiscount();
            weekdayOrWeekendDiscount = String.format("주말 할인: -%s원%n",
                    numberFormat.format(restaurant.weekendDiscount()));
        }
        System.out.print(weekdayOrWeekendDiscount);

        // 특별 할인
        boolean isStar = restaurant.isStar(inputDate);
        if (isStar && isDiscount) {
            calculateTotalDiscount += restaurant.starDiscount();
            System.out.printf("특별 할인: -%s원%n",
                    numberFormat.format(restaurant.starDiscount()));
        }

        // 증정 이벤트
        if (isPresentation && isDiscount) {
            calculateTotalDiscount += restaurant.presentationDiscount();
            System.out.printf("증정 이벤트: -%s원%n",
                    numberFormat.format(restaurant.presentationDiscount()));
        }

        if (!isDiscount) {
            System.out.println("없음");
        }

        // 총혜택 금액
        String totalBenefitAmount = String.format("%s원%n", calculateTotalDiscount);
        System.out.println("\n<총혜택 금액>");
        if (calculateTotalDiscount > 0) {
            totalBenefitAmount = String.format("-%s원%n", numberFormat.format(calculateTotalDiscount));
        }
        System.out.print(totalBenefitAmount);

        // 할인 후 예상 결제 금액
        System.out.println("\n<할인 후 예상 결제 금액>");
        String discountAfterTotalPrice = String.format("%s원%n",
                numberFormat.format(
                        buyer.calculateTotalPrice() - calculateTotalDiscount));
        if (isPresentation) {
            discountAfterTotalPrice = String.format("%s원%n",
                    numberFormat.format(
                            buyer.calculateTotalPrice() - calculateTotalDiscount + restaurant.presentationDiscount()));
        }
        System.out.print(discountAfterTotalPrice);

        // 12월 이벤트 배지
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(eventBadge(calculateTotalDiscount));
    }

    private String eventBadge(int calculateTotalDiscount) {
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
