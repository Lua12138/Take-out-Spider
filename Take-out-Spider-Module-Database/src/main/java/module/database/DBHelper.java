package module.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by forDream on 2017/5/8.
 */
public abstract class DBHelper<T extends Table, ID extends Object> {
    private static DBHelper self;

    /**
     * @param singleton 是否获得单例对象
     * @param clazz     获得对象的类型
     * @return @return 获得全局唯一数据库实例
     */
    public static DBHelper getInstance(boolean singleton, Class<? extends DBHelper> clazz) {
        try {
            if (singleton) {
                if (self == null)
                    synchronized (DBHelper.class) {
                        if (self == null)
                            self = clazz.newInstance();

                    }
                return self;
            } else {
                return clazz.newInstance();
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean singleton;
    private ConnectionSource connectionSource;

    private List<Dao<T, ID>> daos;

    protected DBHelper(boolean singleton) throws SQLException {
        this.singleton = singleton;
        this.connectionSource = new JdbcPooledConnectionSource(this.jdbcString());
        this.daos = new ArrayList<>();
        Dao[] daos = this.createDaos();
        for (Dao dao : daos)
            this.daos.add(dao);
    }

    /**
     * 获得DAO层
     *
     * @param table 数据表
     * @return 获取不到则返回NULL
     */
    public Dao<T, ID> getDao(Class<? extends Table> table) {
        for (Dao<T, ID> dao : this.daos)
            if (table.equals(dao.getDataClass()))
                return dao;

        return null;
    }

    protected Dao<? extends Table, ?> createDao(Class<? extends Table> table) {
        try {
            return DaoManager.createDao(this.connectionSource, table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭全局数据库连接
     *
     * @throws IOException
     */
    public void close() throws IOException {
        if (this.singleton)
            synchronized (DBHelper.class) {
                self = null;
            }
        this.connectionSource.close();
    }

    /**
     * eg:<br>
     * <b>SQLite:</b>jdbc:sqlite:db-file-name<br>
     * <b>MySQL:</b>jdbc:mysql://hostname:port/database-name?user=db-username&password=db-password&useUnicode=true&characterEncoding=UTF8&autoReconnect=true
     *
     * @return JDBC连接字符串
     */
    protected abstract String jdbcString();

    /**
     * @return 返回所需的DAO集合
     */
    protected abstract Dao<T, ID>[] createDaos();
}
