package com.jxc.readapis;

import com.jxc.readapis.dto.UserInfosDTO;
import com.jxc.readapis.exceptions.UserNotFoundException;
import com.jxc.readapis.services.UserService;
import fr.esir.jxc.domain.models.Address;
import fr.esir.jxc.domain.models.Article;
import fr.esir.jxc.domain.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadapisApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    private final Address address0 = new Address("postalCode0", "street0", 0, "complement0");
    private final Address address1 = new Address("postalCode1", "street1", 1, "complement1");
    private final Address address2 = new Address("postalCode2", "street2", 2, "complement2");
    private final Address address3 = new Address("postalCode3", "street3", 3, "complement3");

    private final Article article0 = new Article("id0", "www.url0.fr", "init", "init", Arrays.asList("tag0, tag1, tag2"), Arrays.asList("suggestedTag0, suggestedTag1"));
    private final Article article1 = new Article("id1", "www.url1.fr","init", "init", Arrays.asList("tag0, tag1, tag2"), Arrays.asList("suggestedTag0, suggestedTag1"));
    private final Article article2 = new Article("id2", "www.url2.fr", "init", "init",Arrays.asList("tag0, tag1, tag2"), Arrays.asList("suggestedTag0, suggestedTag1"));
    private final Article article3 = new Article("id3", "www.url3.fr","init", "init", Arrays.asList("tag0, tag1, tag2"), Arrays.asList("suggestedTag0, suggestedTag1"));

    private final User user0 = new User("0", "user0", "Pwd/0", address0);
    private final User user1 = new User("1", "user1", "Pwd/1", address1, Arrays.asList(user0.getEmail()));
    private final User user2 = new User("2", "user2", "Pwd/2", address2, Arrays.asList(user0.getEmail(), user1.getEmail()) );
    private final User user3 = new User("3", "user3", "Pwd/3", address3, Arrays.asList(user0.getEmail(), user1.getEmail(), user2.getEmail()));

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
        assertEquals(testUser.getEmail(), user0.getEmail());
        assertEquals(testUser.getUsername(), user0.getUsername());
        assertEquals(testUser.getPassword(), user0.getPassword());
        assertEquals(testUser.getAddress(), user0.getAddress());
        assertEquals(testUser.getFriends(), user0.getFriends());

    }

    @Test
    public void testFindUserByEmail() {

        userService.save(user0);

        UserInfosDTO testUser = userService.getUserByEmail(user0.getEmail());

        assertNotNull(testUser);
        assertEquals(testUser.getEmail(), user0.getEmail());
        assertEquals(testUser.getUsername(), user0.getUsername());
        assertEquals(testUser.getAddress(), user0.getAddress());

    }

    @Test
    public void testFindUserByWrongEmail() {

        userService.save(user0);

        try {
            UserInfosDTO testUser = userService.getUserByEmail("wrongEmail");
        }catch (UserNotFoundException e){
            assert (e.getMessage().contains("wrongEmail"));
        }

    }

    @Test
    public void testListFriends() {

        userService.save(user0);

        User testUser = userService.save(user1);

        List<UserInfosDTO> friends = this.userService.getUserFriends(testUser.getEmail());
        assertEquals(friends.size(), 1);

        UserInfosDTO friend = friends.get(0);
        assertNotNull(friend);
        assertEquals(friend.getEmail(), user0.getEmail());
        assertEquals(friend.getUsername(), user0.getUsername());
        assertEquals(friend.getAddress(), user0.getAddress());

    }

    @Test
    public void testListFriendsWrongEmail() {

        userService.save(user0);
        userService.save(user1);

        User testUser = userService.save(user3);

        List<UserInfosDTO> friends = this.userService.getUserFriends(testUser.getEmail());

        // 3 emails were in the list of friends of user3, but we did not save user2
        // So we have in the list a email not corresponding to a actual user
        assertEquals(friends.size(), 2);

        UserInfosDTO friend = friends.get(0);
        assertNotNull(friend);
        assertEquals(friend.getEmail(), user0.getEmail());
        assertEquals(friend.getUsername(), user0.getUsername());
        assertEquals(friend.getAddress(), user0.getAddress());

    }

    public void initElasticSearch(){
        esTemplate.deleteIndex(User.class);
        esTemplate.createIndex(User.class);
        esTemplate.putMapping(User.class);
        esTemplate.refresh(User.class);
    }

}
