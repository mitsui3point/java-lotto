package lotto.model;

import lotto.model.enums.Ranking;
import lotto.util.NumbersCreator;

import java.util.*;

import static lotto.model.enums.Ranking.*;

public class Buyer {

    public static final int LOTTO_PRICE = 1000;
    private final List<Lotto> lottoes;
    private Buyer(final List<Lotto> lottoes) {
        this.lottoes = Collections.unmodifiableList(lottoes);
    }

    public static Buyer of(int buyCount, NumbersCreator numbersCreator) {
        List<Lotto> result = new ArrayList<>();
        for (int index = 0; index < buyCount; index++) {
            Lotto autoGeneratedLotto = shuffledLotto(result, numbersCreator);
            result.add(autoGeneratedLotto);
        }
        return new Buyer(result);
    }

    public List<Lotto> value() {
        return this.lottoes;
    }

    private static Lotto shuffledLotto(List<Lotto> result, NumbersCreator numbersCreator) {
        Lotto lotto = Lotto.of(numbersCreator);
        while (isDuplicatedLotto(result, lotto)) {
            lotto = Lotto.of(numbersCreator);
        }
        return lotto;
    }

    private static boolean isDuplicatedLotto(List<Lotto> result, Lotto lotto) {
        return result.contains(lotto);
    }

    public Rankings rankings(Seller seller) {
        Map<Ranking, Integer> rankings = new HashMap<>();
        Arrays.stream(values())
                .filter(ranking -> !ranking.equals(NONE))
                .forEach(ranking -> rankings.put(ranking, rankingCount(seller, ranking)));
        return new Rankings(rankings);
    }

    private int rankingCount(Seller seller, Ranking fourth) {
        long count = this.lottoes.stream()
                .map(lotto -> lotto.compare(seller.winningLotto()))
                .filter(fourth::equals)
                .count();
        return Long.valueOf(count)
                .intValue();
    }
}
