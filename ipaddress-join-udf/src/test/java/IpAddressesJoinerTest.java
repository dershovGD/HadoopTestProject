import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.junit.Test;

import static org.junit.Assert.*;

public class IpAddressesJoinerTest {
    @Test
    public void checkEvaluatingGoodCase() throws HiveException {

        String network = "99.38.94.0/24";
        String ip = "99.38.94.72";
        IpAddressesJoiner ipAddressesJoiner = new IpAddressesJoiner();
        assertTrue(ipAddressesJoiner.evaluate(network, ip));

        String network2 = "192.167.1.2/24";
        String ip2 = "192.168.1.10";
        assertFalse(ipAddressesJoiner.evaluate(network2, ip2));
    }

}