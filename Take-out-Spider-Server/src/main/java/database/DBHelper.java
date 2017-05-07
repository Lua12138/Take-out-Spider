package database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by forDream on 2017/5/7.
 * 数据库支撑类
 */
public class DBHelper {
    private static DBHelper self;

    /**
     * @return 获得全局唯一数据库实例
     */
    public static DBHelper getInstance() {
        if (self == null)
            synchronized (DBHelper.class) {
                if (self == null)
                    try {
                        self = new DBHelper();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
            }
        return self;
    }

    private ConnectionSource connectionSource;
    private Dao<Table_Shop, Integer> shopDao;

    private DBHelper() throws SQLException {
        final String jdbc = "jdbc:sqlite:log.db";
        this.connectionSource = new JdbcPooledConnectionSource(jdbc);
        this.shopDao = DaoManager.createDao(this.connectionSource, Table_Shop.class);
    }

    /**
     * @return 获得店铺表DAO层
     */
    public Dao<Table_Shop, Integer> getShopDao() {
        return shopDao;
    }

    /**
     * 关闭全局数据库连接
     *
     * @throws IOException
     */
    public void close() throws IOException {
        synchronized (DBHelper.class) {
            self = null;
        }
        this.connectionSource.close();
    }
}
