package com.jxc.readapis.services;

import fr.esir.jxc.domain.models.analytics.UserAdded;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class UserAnalyticsServiceImpl implements UserAnalyticsService {

    @Autowired
    private ElasticsearchOperations elasticsearchTemplate;

    @Override
    public UserAdded save(UserAdded userAdded) {
        Assert.notNull(userAdded, "Cannot save null entity.");

        this.elasticsearchTemplate.putMapping(UserAdded.class);
        this.elasticsearchTemplate.index(new IndexQueryBuilder().withObject(userAdded).build());
        this.elasticsearchTemplate.refresh(UserAdded.class);

        return userAdded;
    }

    public String delete(UserAdded userAdded) {
        return this.elasticsearchTemplate.delete(UserAdded.class, userAdded.getId());
    }


    public UserAdded findOneById(String id) {
        Collection<String> ids = new ArrayList<String>();
        ids.add(id);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withIds(ids).build();
        List<UserAdded> result = this.elasticsearchTemplate.queryForList(searchQuery, UserAdded.class);

        return result.get(0);
    }

    public List<UserAdded> findAllUserAdded() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().build();
        return this.elasticsearchTemplate.queryForList(searchQuery, UserAdded.class);
    }


    @Override
    public List<UserAdded> findAllUserAddedByDateInterval(Date dateDebut, Date dateFin) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.rangeQuery("creationDate").gte(dateDebut).lte(dateFin))
                .build();

        return this.elasticsearchTemplate.queryForList(searchQuery, UserAdded.class);
    }
}
