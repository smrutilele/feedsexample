package com.feedsexample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.feedsexample.model.FeedsUser;
import com.feedsexample.output_pojo.FeedsOutput;
import com.feedsexample.service.impl.FeedsExampleService;
import com.feedsexample.util.ApplicationProperties;
import java.util.List;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static FeedsExampleService service;
    private static BasicDataSource ds;
    private static final ObjectMapper mapper;
    private static JsonNodeFactory factory;

    public static void main(String[] args) {
        ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:~/feedsexample");
        ds.setUsername("sa");
        ds.setPassword("");
        service = new FeedsExampleService(ds);
        LOGGER.info("Creating Routes");
        App.setupRoutes();
    }

    private static void setupRoutes() {
        Spark.get("/", (request, response) -> {
            response.redirect("/login");
            return null;
        }
        );
        Spark.after((req, res) -> {
            res.type("application/json");
        }
        );
        Spark.get("/feeds/:userName", (request, response) -> {
            ObjectNode finalOutout = null;
            String userName = request.params(":userName");
            int feedsLimit = ApplicationProperties.getInt("feeds.limit");
            LOGGER.info("userName = " + userName + ", feedsLimit = " + feedsLimit);
            FeedsUser appUser = service.getUserbyUserName(userName);
            if (appUser == null) {
                finalOutout = factory.objectNode();
                finalOutout.put("message", "User not found");
            } else {
                List messages = service.getUserFollowerMessages(appUser, feedsLimit);
                FeedsOutput output = new FeedsOutput(userName, messages, messages.size());
                finalOutout = mapper.valueToTree(output);
            }
            return finalOutout;
        }
        );
        Spark.after((request, response) -> {
            response.type("application/json");
        }
        );
        Spark.get("/login", (request, response) -> {
            ObjectNode finalOutout = factory.objectNode();
            finalOutout.put("message", "Provide login / register feature here");
            return finalOutout;
        }
        );
        Spark.after((request, response) -> {
            response.type("application/json");
        }
        );
    }

    static {
        mapper = new ObjectMapper();
        factory = mapper.getNodeFactory();
    }
}
