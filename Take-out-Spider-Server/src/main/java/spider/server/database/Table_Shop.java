package spider.server.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import module.database.Table;

/**
 * Created by forDream on 2017/5/7.
 * 店铺信息表
 */
@DatabaseTable(tableName = "shop")
public class Table_Shop implements Table {
    /**
     * 店铺ID，根据爬取结果自行设置
     */
    //@DatabaseField(generatedId = true, allowGeneratedIdInsert = true) // 自增id
    @DatabaseField(id = true)  // 自己设置
    private int id;
    /**
     * 店铺名称
     */
    @DatabaseField
    private String shopName;
    /**
     * 店铺地址
     */
    @DatabaseField
    private String shopUrl;
    /**
     * 店铺折扣，JSON
     */
    @DatabaseField
    private String shopDiscount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public String getShopDiscount() {
        return shopDiscount;
    }

    public void setShopDiscount(String shopDiscount) {
        this.shopDiscount = shopDiscount;
    }

    @Override
    public String toString() {
        return "Table_Shop{" +
                "id=" + id +
                ", shopName='" + shopName + '\'' +
                ", shopUrl='" + shopUrl + '\'' +
                ", shopDiscount='" + shopDiscount + '\'' +
                '}';
    }
}
