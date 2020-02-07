package com.jxc.readapis;


import com.jxc.readapis.services.UserAnalyticsService;
import fr.esir.jxc.domain.models.analytics.UserAdded;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadapisApplication.class)
public class UserAnalyticsServiceTest {

    @Autowired
    private UserAnalyticsService userAnalyticsService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    private final UserAdded userAdded1 = new UserAdded("01", Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
    private final UserAdded userAdded2 = new UserAdded("02", Date.from(LocalDate.of(2019, Month.DECEMBER, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    private final UserAdded userAdded3 = new UserAdded("03", Date.from(LocalDate.of(2019, Month.JANUARY, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));

    @Before
    public void before() {
        initElasticSearch();
    }

    @After
    public void after() {
        initElasticSearch();
    }

    @Test
    public void testSave() {
        UserAdded testUserAdded = userAnalyticsService.save(userAdded1);

        assertNotNull(testUserAdded);
        assertEquals(userAdded1.getId(), testUserAdded.getId());
        assertEquals(userAdded1.getCreationDate(), testUserAdded.getCreationDate());
    }

    @Test
    public void testFindAllUsersAdded() {
        List<UserAdded> userAddedList = new ArrayList<UserAdded>();
        userAddedList.add(userAdded1);
        userAddedList.add(userAdded2);

        userAnalyticsService.save(userAdded1);
        userAnalyticsService.save(userAdded2);

        List<UserAdded> userAddedListTest = userAnalyticsService.findAllUserAdded();

        assertEquals(userAddedList.size(), userAddedListTest.size());
        assertTrue(userAddedListTest.contains(userAdded1));
        assertTrue(userAddedListTest.contains(userAdded2));

        // ----------------------------------------------------------------------------
        userAddedList.add(userAdded3);
        userAnalyticsService.save(userAdded3);

        userAddedListTest = userAnalyticsService.findAllUserAdded();

        assertEquals(userAddedList.size(), userAddedListTest.size());
        assertTrue(userAddedListTest.contains(userAdded3));
    }

    @Test
    public void testFindAllUserAddedByDate() {
        LocalDate localDate = LocalDate.now();
        Date dateDebut = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<UserAdded> userAddedList = new ArrayList<UserAdded>();
        userAddedList.add(userAdded1);
        userAddedList.add(userAdded2);

        userAnalyticsService.save(userAdded1);
        userAnalyticsService.save(userAdded2);

        List<UserAdded> userAddedListTest = this.userAnalyticsService.findAllUserAddedByDateInterval(dateDebut, dateDebut);

        assertEquals(1, userAddedListTest.size());
    }

    public void initElasticSearch(){
        esTemplate.deleteIndex(UserAdded.class);
        esTemplate.createIndex(UserAdded.class);
        esTemplate.putMapping(UserAdded.class);
        esTemplate.refresh(UserAdded.class);
    }
}
