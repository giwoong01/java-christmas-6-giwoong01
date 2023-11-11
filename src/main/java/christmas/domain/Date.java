package christmas.domain;

import java.util.List;

public enum Date {
    WEEKEND(List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)),
    STAR(List.of(3, 10, 17, 24, 25, 31));

    private final List<Integer> dates;

    Date(List<Integer> dates) {
        this.dates = dates;
    }

    public List<Integer> getDates() {
        return dates;
    }
}
