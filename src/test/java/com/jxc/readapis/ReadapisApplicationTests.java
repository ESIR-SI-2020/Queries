package com.jxc.readapis;

import com.jxc.readapis.graphql.config.GraphQLErrorHandlerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {GraphQLErrorHandlerConfig.class})
public class ReadapisApplicationTests {

    @Test
    public void contextLoads() {
    }

}
