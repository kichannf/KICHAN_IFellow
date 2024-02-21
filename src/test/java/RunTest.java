import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;


public class RunTest {

    final int cost = 500;
    final float discount_amount = 0.5f;

    @ParameterizedTest(name = "Присваивание статуса от возраста")
    @CsvSource(value = {
            "retiree, 66",
            "student, 19",
            "kid, 3",
            "person, 40"
    })
    void testPersonsStatus(String category, int age) {
        Assertions.assertEquals(category, personsStatus(age));
    }

    @ParameterizedTest(name = "Доступна ли скидка")
    @ValueSource(strings = {"kid", "student", "retiree"})
    void testHaveDiscount(String status) {
        Assertions.assertTrue(haveDiscount(status));
    }

    @Test
    @DisplayName("Стоимость билета")
    void testCostTicket(){
        Assertions.assertEquals(500, costTicket(false));
    }

    @Test
    @DisplayName("Запрет отрицательного возраста")
    void testCase() {
        Assertions.assertThrows(Exception.class, () -> personsStatus(-1));
    }

    String personsStatus(int age){
        if (3 <= age && age < 10){
            return "kid";
        } else if (18 < age && age < 25) {
            return "student";
        } else if (65 < age && age <= 100) {
            return "retiree";
        }else return "person";
    }
    boolean haveDiscount(String category) {
        String[] discount_categories = {"student", "retiree", "kid"};
        return Arrays.asList(discount_categories).contains(category);
    }

    float costTicket(boolean discount) {
        return (discount) ? cost * discount_amount : cost;
    }
}

