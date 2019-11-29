package com.jxc.readapis.services;

import fr.esir.jxc.domain.models.analytics.UserAdded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserAnalyticsServiceImpl implements UserAnalyticsService {

    @Autowired
    private ElasticsearchOperations elasticsearchTemplate;

    public List<UserAdded> findAllUserAdded() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().build();
        return this.elasticsearchTemplate.queryForList(searchQuery, UserAdded.class);
    }


    @Override
    public List<UserAdded> findAllUserAddedByDate (Date date) {

        return null;
    }
}
