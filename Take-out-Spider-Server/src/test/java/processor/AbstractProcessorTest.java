package processor;

import org.junit.Assert;
import org.junit.Test;
import us.codecraft.webmagic.Page;

/**
 * Created by forDream on 2017/5/7.
 */
public class AbstractProcessorTest {
    @Test
    public void staticMethodTest() throws InterruptedException {
        AbstractProcessor processor = new AbstractProcessor() {

            @Override
            public void process(Page page) {
            }
        };
        // 循环测试
        for (int times = 0; times < 100; times++) {
            {
                String ua = processor.randomUseragent(true).toLowerCase();
                Assert.assertTrue("Assert fail with UA " + ua, ua.contains("spider") || ua.contains("bot"));
            }

            {
                String ua = processor.randomUseragent(false).toLowerCase();
                Assert.assertFalse("Assert fail with UA " + ua, ua.contains("spider") || ua.contains("bot"));
            }

            {
                int flag = 0;
                boolean last = false;
                int count = 0;
                while (flag < 2) {
                    count++;

                    String ua = processor.randomUseragent().toLowerCase();
                    if (last == (ua.contains("spider") || ua.contains("bot"))) {
                        last = !last;
                        flag++;
                    }
                    if (count > 10000) break;
                    Thread.sleep(0, 1);
                }

                Assert.assertFalse("Cannot fetch random UA", count > 1000);
            }
        }
    }
}