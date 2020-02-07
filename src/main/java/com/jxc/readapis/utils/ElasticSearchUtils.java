package com.jxc.readapis.utils;

import org.springframework.data.elasticsearch.core.query.GetQuery;

public class ElasticSearchUtils {

    public static GetQuery getQuery(String id){
        final GetQuery query = new GetQuery();
        query.setId(id);
        return query;
    }



}
