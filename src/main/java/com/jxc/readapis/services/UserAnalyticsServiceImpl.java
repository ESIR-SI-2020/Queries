package com.jxc.readapis.services;

import fr.esir.jxc.domain.models.analytics.UserAdded;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.elasticsearch.client.Client;
import org.springframework.stereotype.Repository;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

import java.util.*;

@Repository
public class  UserAnalyticsServiceImpl implements UserAnalyticsService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

   /* @Value("${elasticsearch.index.name}")
    private String indexName;

    @Value("${elasticsearch.user.type}")
    private String userTypeName;*/

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Override
    public UserAdded newUserAdded(UserAdded userAdded) {
        IndexQuery userQuery = new IndexQuery();
        userQuery.setIndexName("pocket");
        userQuery.setType("user_added");
        userQuery.setObject(userAdded);

        LOG.info("user_added indexed: {}", esTemplate.index(userQuery));
        esTemplate.refresh("pocket");

        return userAdded;
    }

    @Override
    public UserAdded getUserAddedById(String id) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("pocket").withTypes("user_added")
                .withFilter(QueryBuilders.matchQuery("id", id)).build();
        List<UserAdded> userAdded = esTemplate.queryForList(searchQuery, UserAdded.class);
        if(!userAdded.isEmpty()) {
            return userAdded.get(0);
        }
        return null;
    }

    @Override
    public List<UserAdded> getAllUserAdded() {
        SearchQuery getAllQuery = new NativeSearchQueryBuilder()
                .withIndices("pocket").withTypes("user_added")
                .withQuery(matchAllQuery()).build();
        return esTemplate.queryForList(getAllQuery, UserAdded.class);
    }

    @Override
    public  int numberOfUserAdded(){
        SearchQuery getAllQuery = new NativeSearchQueryBuilder()
                .withIndices("pocket").withTypes("user_added")
                .withQuery(matchAllQuery()).build();
        List<UserAdded> userAdded =  esTemplate.queryForList(getAllQuery, UserAdded.class);

        return userAdded.size();
    }

    public UserAdded getBySpecificDate(String date) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("pocket").withTypes("user_added")
                .withQuery(QueryBuilders.matchQuery("creationDate", date))
                .build();
        List<UserAdded> userAdded = esTemplate.queryForList(searchQuery, UserAdded.class);
        if (!userAdded.isEmpty()) {
            return userAdded.get(0);
        }
        return null;
    }

    public String delete(UserAdded userAdded) {
        return esTemplate.delete(UserAdded.class, userAdded.getId());
    }
}
