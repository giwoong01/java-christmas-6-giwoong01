package christmas.controller;

import christmas.domain.Buyer;
import christmas.domain.Restaurant;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.text.NumberFormat;

public class GameMachine {
    private final Restaurant restaurant;
    private final InputView inputView;
    private final OutputView outputView;

    private final NumberFormat numberFormat;
    private int calculateTotalDiscount;

    public GameMachine() {
        this.restaurant = new Restaurant();
        this.inputView = new InputView();
        this.outputView = new OutputView();
        numberFormat = NumberFormat.getInstance();
        calculateTotalDiscount = 0;
    }

    public void start() {
        int inputDate = inputView.inputVisitDate();
        Buyer buyer = inputBuyerInfo();
        System.out.println(buyer.getMenuAndCounts());

        outputView.previewEventBenefitMessage(inputDate);
        showOrderMenu(buyer);

        boolean isPresentation = restaurant.isPresentation(buyer.getTotalPrice());
        showPresentedMenu(isPresentation);
        applyBenefits(buyer, inputDate, isPresentation);
        showTotalBenefitAmount();
        calculateFinalPrice(buyer, isPresentation);
        outputView.eventBadgeMessage(restaurant, calculateTotalDiscount);
    }

    private Buyer inputBuyerInfo() {
        return inputView.inputMenuAndCounts();
    }

    private void showOrderMenu(Buyer buyer) {
        outputView.orderMenuMessage(buyer);
        outputView.totalPriceBeforeDiscountMessage(buyer);
    }

    private void showPresentedMenu(boolean isPresentation) {
        String presentationsMenu = "없음";

        if (isPresentation) {
            presentationsMenu = "샴페인 1개";
        }
        outputView.presentedMenuMessage(presentationsMenu);
    }

    private void applyBenefits(Buyer buyer, int inputDate, boolean isPresentation) {
        benefitsDetails(buyer, inputDate, isPresentation);
    }

    private void showTotalBenefitAmount() {
        String totalBenefitAmount = String.format("%s원%n", calculateTotalDiscount);

        if (calculateTotalDiscount > 0) {
            totalBenefitAmount = String.format("-%s원%n", numberFormat.format(calculateTotalDiscount));
        }
        outputView.totalBenefitAmount(totalBenefitAmount);
    }

    private void calculateFinalPrice(Buyer buyer, boolean isPresentation) {
        discountAfterTotalPrice(buyer, calculateTotalDiscount, isPresentation);
    }

    private void benefitsDetails(Buyer buyer, int inputDate, boolean isPresentation) {
        outputView.benefitsDetailsMessage();
        if (buyer.getTotalPrice() >= 10000) {
            applyChristmasDayDiscount(inputDate);
            applyWeekdayOrWeekendDiscount(inputDate, buyer);
            applyStarDiscount(inputDate);
            applyPresentationDiscount(isPresentation);
            return;
        }

        System.out.println("없음");
    }

    private void applyChristmasDayDiscount(int inputDate) {
        if (restaurant.isChristmasDiscount(inputDate)) {
            calculateTotalDiscount += restaurant.christmasDiscount(inputDate);
            outputView.christmasDayDiscountMessage(inputDate, restaurant);
        }
    }

    private void applyWeekdayOrWeekendDiscount(int inputDate, Buyer buyer) {
        if (restaurant.isWeekday(inputDate)) {
            weekdayDiscount(buyer);
        }

        if (restaurant.isWeekend(inputDate)) {
            weekendDiscount(buyer);
        }
    }

    private void weekdayDiscount(Buyer buyer) {
        String weekdayDiscount = calculateDiscount(restaurant.weekdayDiscount(buyer), "평일");
        outputView.weekdayOrWeekendDiscountMessage(weekdayDiscount);
    }

    private void weekendDiscount(Buyer buyer) {
        String weekendDiscount = calculateDiscount(restaurant.weekendDiscount(buyer), "주말");
        outputView.weekdayOrWeekendDiscountMessage(weekendDiscount);
    }

    private String calculateDiscount(int discountAmount, String discountType) {
        if (discountAmount == 0) {
            return "";
        }
        calculateTotalDiscount += discountAmount;
        return String.format("%s 할인: -%s원%n", discountType, numberFormat.format(discountAmount));
    }

    private void applyStarDiscount(int inputDate) {
        if (restaurant.isStar(inputDate)) {
            calculateTotalDiscount += restaurant.starDiscount();
            outputView.startDiscountMessage(restaurant);
        }
    }

    private void applyPresentationDiscount(boolean isPresentation) {
        if (isPresentation) {
            calculateTotalDiscount += restaurant.presentationDiscount();
            outputView.presentationDiscountMessage(restaurant);
        }
    }

    private void discountAfterTotalPrice(Buyer buyer, int calculateTotalDiscount, boolean isPresentation) {
        String discountAfterTotalPrice = String.format("%s원%n",
                numberFormat.format(
                        buyer.getTotalPrice() - calculateTotalDiscount));
        if (isPresentation) {
            discountAfterTotalPrice = String.format("%s원%n",
                    numberFormat.format(
                            buyer.getTotalPrice() - calculateTotalDiscount + restaurant.presentationDiscount()));
        }
        outputView.discountAfterTotalPriceMessage(discountAfterTotalPrice);
    }

}
