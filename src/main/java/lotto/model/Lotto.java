package lotto.model;

import lotto.util.NumbersCreator;

import java.util.*;

public class Lotto {

    public static final String LOTTO_NUMBER_ALLOWED_BETWEEN_6_TO_45_INTEGER = "로또번호는 6~45 사이의 정수값만 허용됩니다.";
    public static final String LOTTO_NUMBERS_NOT_ALLOWED_DUPLICATED = "로또번호목록은 중복될수 없습니다.";
    public static final String LOTTO_NUMBERS_SIZE_ALLOWED_ONLY_6 = "로또번호목록은 반드시 6개의 로또번호를 입력해야 합니다..(6,7,17,28,39,45)";
    public static final int LOTTO_NUMBERS_SIZE = 6;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    private final List<Integer> lottoNumbers;

    private Lotto(final List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static Lotto of(final NumbersCreator numbersCreator) {
        final Integer[] lottoNumbers = numbersCreator.create();
        List<Integer> result = new ArrayList<>();
        for (int lottoNumber : lottoNumbers) {
            validateLottoNumber(lottoNumber);
            result.add(lottoNumber);
        }
        validateLottoNumbers(result);
        result.sort(Integer::compareTo);
        return new Lotto(Collections.unmodifiableList(result));
    }

    private static void validateLottoNumbers(List<Integer> result) {
        int lottoNumbersSize = result.size();

        int distinctLottoNumbersSize = new HashSet<>(result).size();
        boolean isLottoNumberDuplicated = distinctLottoNumbersSize < lottoNumbersSize;
        if (isLottoNumberDuplicated) {
            throw new IllegalArgumentException(LOTTO_NUMBERS_NOT_ALLOWED_DUPLICATED);
        }

        boolean isLottoNumbersSizeEqualSix = lottoNumbersSize != LOTTO_NUMBERS_SIZE;
        if (isLottoNumbersSizeEqualSix) {
            throw new IllegalArgumentException(LOTTO_NUMBERS_SIZE_ALLOWED_ONLY_6);
        }
    }

    private static void validateLottoNumber(int lottoNumber) {
        boolean isLottoNumberValidRange = lottoNumber < MIN_LOTTO_NUMBER ||
                lottoNumber > MAX_LOTTO_NUMBER;
        if (isLottoNumberValidRange) {
            throw new IllegalArgumentException(LOTTO_NUMBER_ALLOWED_BETWEEN_6_TO_45_INTEGER);
        }
    }

    public List<Integer> numbers() {
        return this.lottoNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
