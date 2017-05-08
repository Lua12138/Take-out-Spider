package spider.scanner.processor;

import spider.scanner.proxy.ProxyAdapter;
import spider.server.processor.AbstractProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.proxy.ProxyPool;

/**
 * Created by forDream on 2017/5/8.
 * 代理服务器检测类
 */
public class ProxyTestProcessor extends AbstractProcessor {
    public ProxyTestProcessor() {
        super();
        System.err.println("Try to create ProxyPool");
        ProxyPool proxyPool = new ProxyAdapter();
        System.err.println("Try to create ProxyPool finished.");
        this.site.setHttpProxyPool(proxyPool)
                .setTimeOut(10000)
                .setRetryTimes(2);
    }

    @Override
    public void process(Page page) {
//        System.err.println("process proxy "+this.site.getHttpProxy());
//        System.err.println(page.getHtml().toString().indexOf(this.site.getHttpProxy().getHostName()));
        System.out.println("Congratulations! Find an new proxy.");
    }
}
