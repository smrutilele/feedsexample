package com.feedsexample.service.impl;

import com.feedsexample.dao.FeedsMessageDao;
import com.feedsexample.dao.FeedsUserDao;
import com.feedsexample.model.FeedsMessage;
import com.feedsexample.model.FeedsUser;
import java.util.List;
import javax.sql.DataSource;

public class FeedsExampleService {
    private FeedsUserDao feedsUserDao;
    private FeedsMessageDao feedsMessageDao;

    public FeedsExampleService(DataSource ds) {
        this.feedsUserDao = new FeedsUserDao(ds);
        this.feedsMessageDao = new FeedsMessageDao(ds);
    }

    public List<FeedsMessage> getUserFollowerMessages(FeedsUser appUser, int feedsLimit) {
        return this.feedsMessageDao.getUserFollowerMessages(appUser, feedsLimit);
    }

    public FeedsUser getUserbyUserName(String userName) {
        return this.feedsUserDao.getUserbyUserName(userName);
    }
}
