package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Buyer;
import christmas.domain.Restaurant;
import java.text.NumberFormat;
import java.util.Arrays;

public class GameMachine {
    private final NumberFormat numberFormat;
    private final Restaurant restaurant;

    public GameMachine() {
        this.restaurant = new Restaurant();
        numberFormat = NumberFormat.getInstance();
    }

    public void start() {
        // 구매자 입력
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플레너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        int inputDate = Integer.parseInt(Console.readLine());

        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String inputMenuAndCount = Console.readLine();
        String[] menuAndCounts = inputMenuAndCount.split(",");
        Buyer buyer = new Buyer(Arrays.stream(menuAndCounts).toList());

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

        System.out.println("\n<혜택 내역>");
        // 크리스마스 디데이
        boolean isChristmasDiscount = restaurant.isChristmasDiscount(inputDate);
        if (isChristmasDiscount) {
            System.out.printf("크리스마스 디데이 할인: -%s원%n",
                    numberFormat.format(restaurant.christmasDiscount(inputDate)));
        }

        // 평일 할인
        String weekdayOrWeekendDiscount = "";
        boolean isWeekend = restaurant.isWeekend(inputDate);
        if (!isWeekend) {
            weekdayOrWeekendDiscount = String.format("평일 할인: -%s원%n",
                    numberFormat.format(restaurant.weekdayDiscount()));
        }

        // 주말 할인
        if (isWeekend) {
            weekdayOrWeekendDiscount = String.format("주말 할인: -%s원%n",
                    numberFormat.format(restaurant.weekendDiscount()));
        }

        System.out.println(weekdayOrWeekendDiscount);

        // 특별 할인

        // 증정 이벤트

    }

}
