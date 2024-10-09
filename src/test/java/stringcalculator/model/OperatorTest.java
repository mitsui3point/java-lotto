package stringcalculator.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static stringcalculator.model.Operator.*;

public class OperatorTest {
    @Test
    void 한개의_사칙연산을_성공한다_덧셈() {
        Operand actual = SUM.calculate(Operand.of("2"), Operand.of("3"));
        Operand expected = Operand.of("5");
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 한개의_사칙연산을_성공한다_뺄셈() {
        Operand actual = SUBTRACT.calculate(Operand.of("3"), Operand.of("2"));
        Operand expected = Operand.of("1");
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 한개의_사칙연산을_성공한다_곱셈() {
        Operand actual = MULTIPLY.calculate(Operand.of("3"), Operand.of("2"));
        Operand expected = Operand.of("6");
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 한개의_사칙연산을_성공한다_나눗셈() {
        Operand actual = DIVIDE.calculate(Operand.of("3"), Operand.of("2"));
        Operand expected = Operand.of("1");
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 특수문자_4개를_연산자타입으로_바꿔준다() {
        Operator actualSum = convertToOperator("+");
        Operator actualSubtract = convertToOperator("-");
        Operator actualMultiply = convertToOperator("*");
        Operator actualDivide = convertToOperator("/");

        Assertions.assertThat(actualSum).isEqualTo(SUM);
        Assertions.assertThat(actualSubtract).isEqualTo(SUBTRACT);
        Assertions.assertThat(actualMultiply).isEqualTo(MULTIPLY);
        Assertions.assertThat(actualDivide).isEqualTo(DIVIDE);
    }

    @Test
    void 특수문자_4개_이외에_다른_값이_입력되면_오류() {
        Assertions.assertThatThrownBy(() -> {
                    convertToOperator("f");
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_ALLOWED_OPERATOR_ANOTHER_STRINGS);
    }
}