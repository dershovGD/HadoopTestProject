import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Record {
    private final String productName;
    private final BigDecimal productPrice;
    private final Timestamp date;
    private final String productCategory;
    private final String ipAddress;

    public Record(String productName, BigDecimal productPrice, Timestamp date, String productCategory, String ipAddress) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.date = date;
        this.productCategory = productCategory;
        this.ipAddress = ipAddress;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public StringBuilder csvFormat() {
        StringBuilder builder = new StringBuilder();
        builder.append(productName).append(",")
                .append(productPrice).append(",")
                .append(date).append(",")
                .append(productCategory).append(",")
                .append(ipAddress);
        return builder;
    }
}
