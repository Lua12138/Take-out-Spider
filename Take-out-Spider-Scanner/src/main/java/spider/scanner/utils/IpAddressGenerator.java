package spider.scanner.utils;

import java.util.Arrays;

/**
 * Created by forDream on 2017/5/8.
 * 全局唯一全网IP地址递增发生器
 */
public class IpAddressGenerator {
    private static IpAddressGenerator self;

    public static IpAddressGenerator getInstance() {
        if (self == null)
            synchronized (IpAddressGenerator.class) {
                if (self == null) self = new IpAddressGenerator();
            }
        return self;
    }

    public static String convertToStringAddress(int ip) {
        String[] strings = new String[4];
        strings[3] = String.valueOf(ip & 0xFF);
        strings[2] = String.valueOf((ip >>> 8) & 0xFF);
        strings[1] = String.valueOf((ip >>> 16) & 0xFF);
        strings[0] = String.valueOf((ip >>> 24) & 0xFF);
        return String.join(".", strings);
    }

    public static int convertToIntegerAddress(String ip) {
        String[] ips = ip.split("\\.");
        int n = ((Integer.parseInt(ips[0]) & 0xFF) << 24) |
                ((Integer.parseInt(ips[1]) & 0xFF) << 16) |
                ((Integer.parseInt(ips[2]) & 0xFF) << 8) |
                (Integer.parseInt(ips[3]) & 0xFF);
        return (int) n;
    }

    private int[] ips;
    private boolean allFinish;

    private IpAddressGenerator() {
        this.ips = new int[4];
        Arrays.fill(this.ips, 1);
        this.allFinish = false;
    }

    private boolean addIp() {
        if (this.allFinish) return false;
        for (int n = 3; n >= 0; n--) {
            this.ips[n]++;
            if (this.ips[n] > 255)
                this.ips[n] = 1; // 超过网段归位
            else
                break;
        }

        if (ips[0] == ips[1] && ips[2] == ips[3] && ips[0] == ips[3] && ips[0] == 1) {
            this.allFinish = true;
            return false;
        }
        return true;
    }

    public String generator() {
        synchronized (self) {
            if (this.addIp()) {
                return new StringBuilder()
                        .append(this.ips[0]).append('.')
                        .append(this.ips[1]).append('.')
                        .append(this.ips[2]).append('.')
                        .append(this.ips[3]).toString();
            } else
                return null;
        }
    }


}
