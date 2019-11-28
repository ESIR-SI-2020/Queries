package com.jxc.readapis.services;

import fr.esir.jxc.domain.models.analytics.UserAdded;
import fr.esir.jxc.elasticsearch.config.ElasticsearchConfig;
import org.joda.time.DateTimeComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

@Service
public class UserAnalyticsServiceImpl implements UserAnalyticsService {

    @Autowired
    private ElasticsearchOperations elasticsearchTemplate;

    public List<UserAdded> findAll() {
        ElasticsearchConfig config = new ElasticsearchConfig();
        this.elasticsearchTemplate = config.elasticsearchTemplate();

   //     SearchQuery query = new NativeSearchQueryBuilder().withQuery();

        return null;
    }


    @Override
    public List<UserAdded> findAllUserAddedByDate (Date date) {
        Predicate<UserAdded> byCreationDate = userAdded -> DateTimeComparator.getDateOnlyInstance().compare(userAdded.getCreationDate(), date) == 0;

        return null;
    }
}
