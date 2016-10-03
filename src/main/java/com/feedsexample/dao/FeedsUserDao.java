package com.feedsexample.dao;

import javax.sql.DataSource;
import static com.feedsexample.db.Tables.FEEDS_USER;

import com.feedsexample.model.FeedsUser;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import static org.jooq.SQLDialect.H2;
import org.jooq.impl.DSL;

public class FeedsUserDao {
    private DSLContext sql;
    private RecordMapper<Record, FeedsUser> userMapper = r -> {
        FeedsUser u = new FeedsUser();
        u.setUserName(r.getValue(FEEDS_USER.USERNAME));
        u.setPassword(r.getValue(FEEDS_USER.PASSWORD));
        return u;
    };

    public FeedsUserDao(DataSource ds) {
        this.sql = DSL.using(ds, H2);
    }

    public FeedsUser getUserbyUserName(String userName) {
        return this.sql.select()
                .from(FEEDS_USER)
                .where(FEEDS_USER.USERNAME.eq(userName))
                .fetchOne(this.userMapper);
    }
}
