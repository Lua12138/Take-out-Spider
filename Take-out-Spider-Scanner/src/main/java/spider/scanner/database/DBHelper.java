package spider.scanner.database;

import com.j256.ormlite.dao.Dao;

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
        return "jdbc:mysql://192.168.8.68:3306/waimai?user=waimai&password=waimai&useUnicode=true&characterEncoding=UTF8&autoReconnect=true";
    }

    @Override
    protected Dao[] createDaos() {
        return new Dao[]{this.createDao(Table_IP.class)};
    }

    public Dao<Table_IP, Integer> getIps() {
        return this.getDao(Table_IP.class);
    }
}
