package spider.server.database;

import com.j256.ormlite.dao.Dao;
import module.database.Table;

import java.sql.SQLException;

/**
 * Created by forDream on 2017/5/7.
 * 数据库支撑类
 */
public class DBHelper extends module.database.DBHelper {
    public static DBHelper getInstance() {
        return (DBHelper) module.database.DBHelper.getInstance(true, DBHelper.class);
    }

    protected DBHelper() throws SQLException {
        super(true);
    }

    @Override
    protected String jdbcString() {
        return "jdbc:sqlite:log.db";
    }

    @Override
    protected Dao<Table, Object>[] createDaos() {
        return new Dao[]{
                this.createDao(Table_Shop.class)
        };
    }

    public Dao<Table_Shop, Integer> getShopDao() {
        return this.getDao(Table_Shop.class);
    }

//    private static DBHelper self;
//
//    /**
//     * @return 获得全局唯一数据库实例
//     */
//    public static DBHelper getInstance() {
//        if (self == null)
//            synchronized (DBHelper.class) {
//                if (self == null)
//                    try {
//                        self = new DBHelper();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//            }
//        return self;
//    }
//
//    private ConnectionSource connectionSource;
//    private Dao<Table_Shop, Integer> shopDao;
//
//    protected DBHelper() throws SQLException {
//        final String jdbc = "jdbc:sqlite:log.db";
//        this.connectionSource = new JdbcPooledConnectionSource(jdbc);
//        this.shopDao = DaoManager.createDao(this.connectionSource, Table_Shop.class);
//    }
//
//    /**
//     * @return 获得店铺表DAO层
//     */
//    public Dao<Table_Shop, Integer> getShopDao() {
//        return shopDao;
//    }
//
//    /**
//     * 关闭全局数据库连接
//     *
//     * @throws IOException
//     */
//    public void close() throws IOException {
//        synchronized (DBHelper.class) {
//            self = null;
//        }
//        this.connectionSource.close();
//    }
}
