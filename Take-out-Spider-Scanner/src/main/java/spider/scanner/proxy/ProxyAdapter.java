package spider.scanner.proxy;

import org.apache.http.HttpHost;
import spider.scanner.database.DBHelper;
import spider.scanner.utils.IpAddressGenerator;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyPool;

import java.sql.SQLException;

/**
 * Created by forDream on 2017/5/8.
 */
public class ProxyAdapter implements ProxyPool {
    protected static final int[] PORTS = new int[]{80, 81, 808, 8080, 8081, 8998, 8989, 8118, 8888, 9999, 3128, 9797};
    private int portIndex = 0;
    private String address;

    public ProxyAdapter() {
        this.address = IpAddressGenerator.getInstance().generator();
    }

    @Override
    public void returnProxy(HttpHost host, int statusCode) {
        switch (statusCode) {
            case Proxy.SUCCESS:
                try {
                    DBHelper.getInstance().getIps().create(new Table_IP(IpAddressGenerator.convertToIntegerAddress(host.getHostName()), host.getPort()));
                    System.out.printf("Save Proxy %s:%d%n", host.getHostName(), host.getPort());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
//                System.err.println("Proxy Error Code is " + statusCode + " " + host.getHostName() + ":" + host.getPort());
                break;
        }
    }

    @Override
    public Proxy getProxy() {
        if (this.portIndex >= PORTS.length) {
            this.portIndex = 0;
            this.address = IpAddressGenerator.getInstance().generator();
        }
        return new Proxy(new HttpHost(this.address, PORTS[this.portIndex++]), Integer.MAX_VALUE, "", "");
    }

    @Override
    public boolean isEnable() {
        return true;
    }
}
