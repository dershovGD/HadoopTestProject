import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class RecordTest {

    @Test
    public void checkCSVFormat(){
        Record record = new Record("product",
                BigDecimal.ONE,
                Timestamp.valueOf(LocalDateTime.of(2018, 10, 10, 12, 12, 0)),
                "toy",
                "192.168.1.1");
        String expectedResult = "product,1,2018-10-10 12:12:00.0,toy,192.168.1.1";
        assertEquals(expectedResult, record.csvFormat().toString());
    }

}