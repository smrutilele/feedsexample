package com.feedsexample.dao;
import static com.feedsexample.db.Tables.FEEDS_MESSAGE;
import static com.feedsexample.db.Tables.FEEDS_USER_FOLLOWER;

import com.feedsexample.model.FeedsMessage;
import com.feedsexample.model.FeedsUser;
import java.util.List;
import javax.sql.DataSource;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import static org.jooq.SQLDialect.H2;
import org.jooq.impl.DSL;

public class FeedsMessageDao {
    private DSLContext sql;
    private RecordMapper<Record, FeedsMessage> messageMapper = r -> {
        FeedsMessage m = new FeedsMessage();
        m.setMessageId(r.getValue((FEEDS_MESSAGE.MESSAGEID)));
        m.setUserName(r.getValue(FEEDS_MESSAGE.USERNAME));
        m.setTextMessage(r.getValue(FEEDS_MESSAGE.TEXTMESSAGE));
        m.setDateOfPublish(r.getValue(FEEDS_MESSAGE.PUBLISHDATE));
        return m;
    };

    public FeedsMessageDao(DataSource ds) {
        this.sql = DSL.using(ds, H2);
    }

    public List<FeedsMessage> getUserFollowerMessages(FeedsUser user, int feedsLimit) {
        return this.sql.select()
                .from(FEEDS_MESSAGE)
                .where(FEEDS_MESSAGE.USERNAME.in(this.sql.select(FEEDS_USER_FOLLOWER.FOLLOWERUSERNAME)
                        .from(FEEDS_USER_FOLLOWER)
                        .where(FEEDS_USER_FOLLOWER.USERNAME.equal(user.getUserName()))))
                .orderBy(FEEDS_MESSAGE.PUBLISHDATE.desc())
                .limit(feedsLimit)
                .fetch(this.messageMapper);
    }
}
