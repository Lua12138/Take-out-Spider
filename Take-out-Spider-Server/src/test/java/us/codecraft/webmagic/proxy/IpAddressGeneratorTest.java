package us.codecraft.webmagic.proxy;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by forDream on 2017/5/8.
 */
public class IpAddressGeneratorTest {
    public static void method() {
        for (int i = 0; i < 255; i++) {
            System.err.println(IpAddressGenerator.getInstance().generator());
        }
    }

    @Test
    public void test() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 255; i++)
            executorService.submit(() -> IpAddressGeneratorTest.method());

        executorService.shutdown();
        while (!executorService.isTerminated())
            Thread.sleep(1);
    }
}