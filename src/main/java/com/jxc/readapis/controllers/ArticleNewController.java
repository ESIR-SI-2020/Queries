package com.jxc.readapis.controllers;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.esir.jxc.domain.models.analytics.UserAdded;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.*;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analytics")
public class ArticleNewController {

    @Autowired
    private  ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    Client client;
    @PostMapping("/create_useradded")
    public String create(@RequestBody UserAdded userAdded) throws IOException {

        IndexResponse response = client.prepareIndex("pocket", "user", userAdded.getId())
                .setSource(jsonBuilder()
                        .startObject()
                        .field("creationDate", userAdded.getCreationDate())
                        .endObject()
                )
                .get();
        System.out.println("response id:"+response.getId());
        return response.getResult().toString();
    }

    @GetMapping("/useraddedbyid/{id}")
    public Map<String, Object> view(@PathVariable final String id) {
        GetResponse getResponse = client.prepareGet("pocket", "user", id).get();
        return getResponse.getSource();
    }

    @GetMapping("/useradded/creationDate/{field}")
    public Map<String, Object> searchByName(@PathVariable final String field) {
        Map<String,Object> map = null;
        SearchResponse response = client.prepareSearch("pocket")
                .setTypes("user")
                .setSearchType(SearchType.QUERY_AND_FETCH)
                .setQuery(QueryBuilders.matchQuery("creationDate", field))
                .get()
                ;
        List<SearchHit> searchHits = Arrays.asList(response.getHits().getHits());
        map =   searchHits.get(0).getSource();
        return map;
    }

  /*  @GetMapping("/all")
    public List<UserAdded> getUsersAdded(String apartmentName) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(org.elasticsearch.index.query.QueryBuilders
                        .matchQuery("apartment_Name", apartmentName)).build();
        Page<UserAdded> sampleEntities =
                elasticsearchTemplate.queryForPage(searchQuery,UserAdded.class);
        return sampleEntities.getContent();
    }*/

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable final String id) {

        DeleteResponse deleteResponse = client.prepareDelete("pocket", "user", id).get();

        System.out.println(deleteResponse.getResult().toString());
        return deleteResponse.getResult().toString();
    }

}
