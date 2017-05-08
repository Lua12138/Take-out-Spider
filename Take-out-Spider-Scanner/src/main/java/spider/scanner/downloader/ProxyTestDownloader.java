package spider.scanner.downloader;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

/**
 * Created by forDream on 2017/5/8.
 */
public class ProxyTestDownloader extends HttpClientDownloader {
    @Override
    protected void onError(Request request) {
//        System.err.println(request);
    }

    @Override
    protected void onSuccess(Request request) {
//        super.onSuccess(request);
    }
}
