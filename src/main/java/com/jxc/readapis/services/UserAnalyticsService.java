package com.jxc.readapis.services;

import java.util.Date;
import java.util.List;

import com.jxc.dbmanager.models.UserAdded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;


public interface UserAnalyticsService {


    List<UserAdded> findAllUserAddedByDate (Date date);

}
