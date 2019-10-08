package com.jxc.app;

import com.jxc.app.models.Address;
import com.jxc.app.models.User;
import com.jxc.app.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    private final Address address0 = new Address("postalCode0", "street0", 0, "complement0");
    private final Address address1 = new Address("postalCode1", "street1", 1, "complement1");
    private final Address address2 = new Address("postalCode2", "street2", 2, "complement2");
    private final Address address3 = new Address("postalCode3", "street3", 3, "complement3");

    private final User user0 = new User("0", "user0", "user0@mail.com", "http://www.photo.com/0.png", "bio0", address0, Arrays.asList("1", "2", "3"));
    private final User user1 = new User("1", "user1", "user1@mail.com", "http://www.photo.com/1.png", "bio1", address1, Arrays.asList("0", "2", "3"));
    private final User user2 = new User("2", "user2", "user2@mail.com", "http://www.photo.com/2.png", "bio2", address2, Arrays.asList("0", "1", "3"));
    private final User user3 = new User("3", "user3", "user3@mail.com", "http://www.photo.com/3.png", "bio3", address3, Arrays.asList("0", "1", "2"));


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

        User testUser = userService.save(user0);

        assertNotNull(testUser);
        assertEquals(testUser.getId(), user0.getId());
        assertEquals(testUser.getAddress(), user0.getAddress());
        assertEquals(testUser.getBio(), user0.getBio());
        assertEquals(testUser.getEmail(), user0.getEmail());
        assertEquals(testUser.getFriendsId(), user0.getFriendsId());
        assertEquals(testUser.getPhotoUrl(), user0.getPhotoUrl());
        assertEquals(testUser.getUsername(), user0.getUsername());

    }

    @Test
    public void testFindUserById() {

        userService.save(user1);

        User testUser = userService.findUserById(user1.getId());

        assertNotNull(testUser);
        assertEquals(testUser.getId(), user1.getId());
        assertEquals(testUser.getAddress(), user1.getAddress());
        assertEquals(testUser.getBio(), user1.getBio());
        assertEquals(testUser.getEmail(), user1.getEmail());
        assertEquals(testUser.getFriendsId(), user1.getFriendsId());
        assertEquals(testUser.getPhotoUrl(), user1.getPhotoUrl());
        assertEquals(testUser.getUsername(), user1.getUsername());

    }

    @Test
    public void testFindAllUsers() {

        List<User> userList = new ArrayList<>();
        userList.add(user2);
        userList.add(user3);

        userService.save(user2);
        userService.save(user3);

        List<User> userListTest = userService.findAllUsers();

        assertEquals(userList.size(), userListTest.size());

        assertTrue(userListTest.contains(user2));
        assertTrue(userListTest.contains(user3));

    }

    public void initElasticSearch(){
        esTemplate.deleteIndex(User.class);
        esTemplate.createIndex(User.class);
        esTemplate.putMapping(User.class);
        esTemplate.refresh(User.class);
    }

}

