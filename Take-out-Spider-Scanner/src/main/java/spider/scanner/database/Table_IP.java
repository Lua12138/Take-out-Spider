package spider.scanner.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import module.database.Table;
import spider.scanner.utils.IpAddressGenerator;

/**
 * Created by forDream on 2017/5/7.
 * 店铺信息表
 */
@DatabaseTable(tableName = "tb_ip")
public class Table_IP implements Table {
    @DatabaseField(id = true)
    private int ip;
    @DatabaseField
    private int port;

    public Table_IP() {
    }

    public Table_IP(int ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Table_IP{" +
                "ip=" + IpAddressGenerator.convertToStringAddress(this.ip) +
                ", port=" + port +
                '}';
    }
}
