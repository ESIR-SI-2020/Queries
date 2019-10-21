package com.jxc.readapis.services;

import com.jxc.dbmanager.models.UserAdded;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.joda.time.DateTimeComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UserAnalyticsServiceImpl implements UserAnalyticsService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public List<UserAdded> findAll() {
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        SearchQuery searchQuery = new NativeSearchQuery(queryBuilder);
        return this.elasticsearchTemplate.queryForList(searchQuery, UserAdded.class);
    }


    @Override
    public List<UserAdded> findAllUserAddedByDate (Date date) {
        Predicate<UserAdded> byCreationDate = userAdded -> DateTimeComparator.getDateOnlyInstance().compare(userAdded.getCreationDate(), date) == 0;

        return this
                .findAll()
                .stream()
                .filter(byCreationDate)
                .collect(Collectors.toList());
    }
}
