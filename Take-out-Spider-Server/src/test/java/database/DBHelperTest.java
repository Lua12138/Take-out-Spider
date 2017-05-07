package database;

import com.j256.ormlite.table.TableUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by forDream on 2017/5/7.
 * 数据库CURD 单元测试
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DBHelperTest {
    @AfterClass
    public static void destroy() throws IOException {
        DBHelper.getInstance().close();
    }

    @Test
    public void a_insert() throws SQLException {
        Table_Shop shop = new Table_Shop();
        shop.setId(1);
        shop.setShopName("test shop");
        shop.setShopDiscount("0");
        shop.setShopUrl("http:///");

        TableUtils.dropTable(DBHelper.getInstance().getShopDao(), true);
        TableUtils.createTable(DBHelper.getInstance().getShopDao());
        int n = DBHelper.getInstance().getShopDao().create(shop);
        Assert.assertEquals(1, n);
    }

    @Test
    public void b_update() throws SQLException {
        Table_Shop shop = DBHelper.getInstance().getShopDao().queryForId(1);
        shop.setShopDiscount("1");
        DBHelper.getInstance().getShopDao().update(shop);
    }

    @Test
    public void c_select() throws SQLException {
        List<Table_Shop> shops = DBHelper.getInstance().getShopDao().queryForAll();
        Assert.assertNotEquals(0, shops.size());
        System.err.println("Generated Id is " + shops.get(0).getId());
        Assert.assertEquals("1", shops.get(0).getShopDiscount());
    }

    @Test
    public void d_delete() throws SQLException {
        DBHelper.getInstance().getShopDao().deleteById(1);
        TableUtils.dropTable(DBHelper.getInstance().getShopDao(), false);
    }
}