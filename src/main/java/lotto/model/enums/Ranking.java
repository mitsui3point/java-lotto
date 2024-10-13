package lotto.model.enums;

import java.util.Arrays;

public enum Ranking {
    NONE(0, 0),
    FOURTH(3, 5000),
    THIRD(4, 50000),
    SECOND(5, 1500000),
    FIRST(6, 2000000000),
    ;

    private final int matchedCount;
    private final long winningAmount;

    Ranking(int matchedCount, long winningAmount) {
        this.matchedCount = matchedCount;
        this.winningAmount = winningAmount;
    }

    public static Ranking result(int matchedCount) {
        return Arrays.stream(Ranking.values())
                .filter(ranking -> Integer.valueOf(matchedCount).equals(ranking.matchedCount))
                .findFirst()
                .orElseGet(() -> Ranking.NONE);
    }

    public long calculate(Integer winningCount) {
        return winningAmount * winningCount;
    }

    public int matchedCount() {
        return matchedCount;
    }

    public long winningAmount() {
        return winningAmount;
    }
}
