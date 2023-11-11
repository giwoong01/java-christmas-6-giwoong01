package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Buyer;
import java.util.Arrays;

public class InputView {
    public int inputVisitDate() {
        while (true) {
            try {
                System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플레너입니다.");
                System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
                String inputDate = Console.readLine().trim();

                validateNumericInput(inputDate);
                validateDateRange(Integer.parseInt(inputDate));
                return Integer.parseInt(inputDate);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void validateNumericInput(String date) {
        try {
            Integer.parseInt(date);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private void validateDateRange(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public Buyer inputMenuAndCounts() {
        while (true) {
            try {
                System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
                String inputMenuAndCount = Console.readLine();
                String[] menuAndCounts = inputMenuAndCount.split(",");

                return new Buyer(Arrays.stream(menuAndCounts).toList());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
