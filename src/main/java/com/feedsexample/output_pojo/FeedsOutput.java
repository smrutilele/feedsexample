package com.feedsexample.output_pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.feedsexample.model.FeedsMessage;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(value=JsonInclude.Include.NON_NULL)
public class FeedsOutput {
    public String userName;
    public List<FeedsMessage> feeds;
    public Integer msgCount;

    public FeedsOutput(String userName, List<FeedsMessage> feeds, int msgCount) {
        this.userName = userName;
        this.feeds = feeds;
        this.msgCount = msgCount;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
