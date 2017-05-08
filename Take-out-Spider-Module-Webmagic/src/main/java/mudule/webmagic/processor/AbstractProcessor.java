package mudule.webmagic.processor;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by forDream on 2017/5/7.
 * 基础页面处理器
 */
public abstract class AbstractProcessor implements PageProcessor {
    protected static List<String> USER_AGENTS_BROWSER;
    protected static List<String> USER_AGENTS_SPIDER;
    private static Random random;

    static {
        USER_AGENTS_BROWSER = loadUseragents("/ua-browser");
        USER_AGENTS_SPIDER = loadUseragents("/ua-spider");
        random = new Random();
    }

    protected static ArrayList<String> loadUseragents(String filepath) {
        InputStream is = Class.class.getResourceAsStream(filepath);
        Scanner scanner = new Scanner(is);
        ArrayList<String> set = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.startsWith("#")) continue;
            set.add(line);
        }

        return set;
    }

    protected Site site;

    /**
     * @return 返回一条随机的UA
     */
    protected String randomUseragent() {
        return randomUseragent((System.currentTimeMillis() & 1) == 1);
    }

    /**
     * @param returnSpiderUA 是否返回蜘蛛UA
     * @return 返回一条符合条件的随机UA
     */
    protected String randomUseragent(boolean returnSpiderUA) {
        if (returnSpiderUA) {
            int n = random.nextInt(USER_AGENTS_SPIDER.size());
            return USER_AGENTS_SPIDER.get(n);
        } else {
            int n = random.nextInt(USER_AGENTS_BROWSER.size());
            return USER_AGENTS_BROWSER.get(n);
        }
    }

    public AbstractProcessor() {
        this.site = Site
                .me()
                .setUserAgent(this.randomUseragent())
                .setRetryTimes(3)
                .setTimeOut(3000)
//                .setDomain(...)
//                .addCookie(...) // 两条一起使用 添加cookie
//                .setHttpProxy(...) // 设置代理
                .setSleepTime(1000 * 10) //两次之间间隔 单位ms

        ;
    }

    @Override
    public Site getSite() {
        return site;
    }
}
