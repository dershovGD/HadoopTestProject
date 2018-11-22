import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Producer {
    public static final LocalDate firstDayOfWeek = LocalDate.of(2018, 10 ,8);
    public static final LocalDate lastDayOfWeek = LocalDate.of(2018, 10, 14);

    private final Random random = new Random();
    private final int scale = 100;
    private final long secondsInDay = 24*60*60;
    private final long desiredMean = secondsInDay /2;
    private final double desiredStDev = desiredMean;
    private final int daysInWeek = 7;

    public Record produce() {
        String productNameGenerated = RandomStringUtils.randomAlphanumeric(10);
        BigDecimal productPriceGenerated = new BigDecimal(Math.abs(random.nextGaussian()) * scale);
        String productCategoryGenerated = RandomStringUtils.randomAlphanumeric(3);
        Timestamp dateTimeGenerated = generateDateTime();
        String ipAddressGenerated = random.nextInt(256) + "." + random.nextInt(256) +
                "." + random.nextInt(256) + "." + random.nextInt(256);

        return new Record(productNameGenerated, productPriceGenerated, dateTimeGenerated, productCategoryGenerated, ipAddressGenerated);

    }

    private Timestamp generateDateTime(){
        final long generatedTime = Math.floorMod((long) (random.nextGaussian() * desiredStDev + desiredMean), secondsInDay);
        LocalTime time = LocalTime.ofSecondOfDay(generatedTime);
        long startOfTheWeek = firstDayOfWeek.toEpochDay();
        LocalDate date = LocalDate.ofEpochDay(startOfTheWeek + random.nextInt(daysInWeek));
        return Timestamp.valueOf(LocalDateTime.of(date, time));

    }
}
