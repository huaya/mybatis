package com.maxlong.study.datasource;

import com.maxlong.study.enums.DatabaseType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/4/24 18:08
 * 类说明
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<DatabaseType>();

    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }

    public static DatabaseType getDatabaseType() {
        return contextHolder.get();
    }

    protected Object determineCurrentLookupKey() {
        return getDatabaseType();
    }
}
