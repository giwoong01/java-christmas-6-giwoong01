package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Buyer;
import java.util.Arrays;

public class GameMachine {

    public void start() {
        // 구매자 입력
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플레너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        int inputDate = Integer.parseInt(Console.readLine());

        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String inputMenuAndCount = Console.readLine();
        String[] menuAndCounts = inputMenuAndCount.split(",");
        Buyer buyer = new Buyer(Arrays.stream(menuAndCounts).toList());

        System.out.printf("12월 %d일 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", inputDate);

        System.out.println("\n<주문 메뉴>");
        System.out.println(buyer);

    }
}
