package spider.scanner;

import spider.scanner.database.DBHelper;
import spider.scanner.database.Table_IP;
import spider.scanner.processor.ProxyTestProcessor;
import spider.scanner.utils.IpAddressGenerator;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import java.sql.SQLException;

/**
 * Created by forDream on 2017/5/8.
 */
public class Startup {
    public static void main(String[] args) throws InterruptedException, SQLException {

        //  数据库读写测试
        Table_IP ip = new Table_IP(IpAddressGenerator.convertToIntegerAddress("255.255.255.255"), 65536);
        DBHelper.getInstance().getIps().create(ip);
        DBHelper.getInstance().getIps().delete(ip);

        System.err.println("Try to Create Process.");
        ProxyTestProcessor proxyTestProcessor = new ProxyTestProcessor();

        System.err.println("Try to Create Spider.");
        long n = 50739007500L;
        final String http = "http://httpbin.org/get?";
        Spider spider = Spider.create(proxyTestProcessor)
//                .setDownloader(new ProxyTestDownloader())
                .setScheduler(new RedisScheduler("192.168.8.68"))
                .thread(4096)
                .addUrl(http);

        System.err.println("Create Spider Finished.");

        spider.runAsync();

        System.err.println("Start Spider Finished.");

        while (n > 0) {
            spider.addUrl(http + n--);
            Thread.sleep(50);
        }

        System.err.println("All task dispatcher over. wait for result");

        while (true)
            Thread.sleep(10);
    }
}
