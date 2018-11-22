import org.junit.Test;

import java.math.BigDecimal;
import java.time.Period;

import static org.junit.Assert.assertTrue;

public class ProducerTest {


    @Test
    public void checkDate() {

        for (int i = 0; i < Main.numberToTest; i++) {
            Record record = new Producer().produce();
            Period period1 = Period.between(record.getDate().toLocalDateTime().toLocalDate(), Producer.lastDayOfWeek);
            assertTrue(period1.getDays() >= 0 && period1.getDays() < 7);

            Period period2 = Period.between(Producer.firstDayOfWeek, record.getDate().toLocalDateTime().toLocalDate());
            assertTrue(period2.getDays() >= 0 && period2.getDays() < 7);
        }
    }

    @Test
    public void checkPrice() {
        for (int i = 0; i < Main.numberToTest; i++){
            Record record = new Producer().produce();
            assertTrue(record.getProductPrice().compareTo(BigDecimal.ZERO) >= 0);
        }
    }

}