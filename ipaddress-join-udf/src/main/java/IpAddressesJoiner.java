import org.apache.commons.net.util.SubnetUtils;
import org.apache.hadoop.hive.ql.exec.UDF;

public class IpAddressesJoiner extends UDF {
    public Boolean evaluate(String network, String ip) {
        return new SubnetUtils(network).getInfo().isInRange(ip);
    }
}
