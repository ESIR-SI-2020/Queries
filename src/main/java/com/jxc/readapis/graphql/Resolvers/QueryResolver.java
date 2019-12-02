package com.jxc.readapis.graphql.Resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jxc.readapis.graphql.exceptions.GraphqlServerSideException;
import com.jxc.readapis.graphql.models.FilterArticleInput;
import com.jxc.readapis.graphql.models.FilterUserInput;
import com.jxc.readapis.graphql.models.enums.FilterArticleEnum;
import com.jxc.readapis.graphql.models.enums.FilterUserEnum;
import fr.esir.jxc.domain.models.Article;
import fr.esir.jxc.domain.models.User;
import fr.esir.jxc.elasticsearch.config.ElasticsearchConfig;
import fr.esir.jxc.elasticsearch.repositories.ArticleRepository;
import fr.esir.jxc.elasticsearch.repositories.UserRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.*;


@Component
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ElasticsearchConfig elasticsearchConfig;

    /*----------------------- USER -----------------------*/

    public List<User> findAllUsers(){
        return userRepository.findAll().getContent();
    }

    public List<User> findAllUsersFilterBy(FilterUserEnum filter, FilterUserInput value) {
        List<User> users = userRepository.findAll().getContent();
        List<User> filteredUsers = new ArrayList<>();
        switch (filter) {
            case email:
                if(!StringUtils.isEmpty(value.getEmail())){
                    Optional<User> user = userRepository.findById(value.getEmail());
                    user.ifPresent(filteredUsers::add);
                } else {
                    throw new GraphqlServerSideException("Unsupported value. In order to filter by email, value must be a String");
                }
                break;

            case username:
                if(!StringUtils.isEmpty(value.getUsername())){
                    SearchQuery searchQuery = new NativeSearchQueryBuilder()
                            .withQuery(matchQuery("username",value.getUsername()))
                            .build();
                    filteredUsers.addAll(elasticsearchConfig.elasticsearchTemplate().queryForList(searchQuery, User.class));
                } else {
                    throw new GraphqlServerSideException("Unsupported value. In order to filter by username, value must be a String");
                }
                break;

            case address:
                System.out.println(value.getAddress());
                if(value.getAddress()!=null && !StringUtils.isEmpty(value.getAddress().getPostalCode()) && !StringUtils.isEmpty(value.getAddress().getStreet()) && !StringUtils.isEmpty(value.getAddress().getComplement()) && value.getAddress().getStreetNumber() != 0){
                    QueryBuilder builder = boolQuery()
                            .must(matchAllQuery())
                            .filter(matchQuery("address.postalCode", value.getAddress().getPostalCode()))
                            .filter(matchQuery("address.street", value.getAddress().getStreet()))
                            .filter(matchQuery("address.streetNumber", value.getAddress().getStreetNumber()))
                            .filter(matchQuery("address.complement", value.getAddress().getComplement()));
                    SearchQuery searchQuery = new NativeSearchQueryBuilder()
                            .withQuery(builder)
                            .build();
                    filteredUsers.addAll(elasticsearchConfig.elasticsearchTemplate().queryForList(searchQuery, User.class));
                } else {
                    throw new GraphqlServerSideException("Unsupported value. In order to filter by address, value must be an Address");
                }
                break;

            case friend:
                if(!StringUtils.isEmpty(value.getFriend())){
                    QueryBuilder builder = boolQuery()
                            .must(matchAllQuery())
                            .filter(termQuery("friends", value.getFriend()));
                    SearchQuery searchQuery = new NativeSearchQueryBuilder()
                            .withQuery(builder)
                            .build();
                    filteredUsers.addAll(elasticsearchConfig.elasticsearchTemplate().queryForList(searchQuery, User.class));
                } else {
                    throw new GraphqlServerSideException("Unsupported value. In order to filter by friend, value must be a String");
                }
                break;

            default:
                throw new GraphqlServerSideException("Unsupported filter: " + filter + "Filter must be : " + Arrays.toString(FilterUserEnum.values()));
        }
        filteredUsers.sort(Comparator.comparing(User::getEmail));
        return filteredUsers;
    }

    /*----------------------- ARTICLE -----------------------*/

    public List<Article> findAllArticles(){
        return articleRepository.findAll().getContent();
    }

    public List<Article> findAllArticlesFilterBy(FilterArticleEnum filter, FilterArticleInput value) {
        List<Article> articles = articleRepository.findAll().getContent();
        List<Article> filteredArticles = new ArrayList<>();
        switch (filter) {
            case id:
                if(!StringUtils.isEmpty(value.getId())){
                    Optional<Article> article = articleRepository.findById(value.getId());
                    article.ifPresent(filteredArticles::add);
                } else {
                    throw new GraphqlServerSideException("Unsupported value. In order to filter by id, value must be a String");
                }
                break;

            case url:
                if(!StringUtils.isEmpty(value.getUrl())){
                    SearchQuery searchQuery = new NativeSearchQueryBuilder()
                            .withQuery(matchQuery("url",value.getUrl()))
                            .build();
                    filteredArticles.addAll(elasticsearchConfig.elasticsearchTemplate().queryForList(searchQuery, Article.class));
                } else {
                    throw new GraphqlServerSideException("Unsupported value. In order to filter by url, value must be a String");
                }
                break;

            case owner:
                if(!StringUtils.isEmpty(value.getOwner())){
                    SearchQuery searchQuery = new NativeSearchQueryBuilder()
                            .withQuery(matchQuery("owner",value.getOwner()))
                            .build();
                    filteredArticles.addAll(elasticsearchConfig.elasticsearchTemplate().queryForList(searchQuery, Article.class));
                } else {
                    throw new GraphqlServerSideException("Unsupported value. In order to filter by owner, value must be a String");
                }
                break;

            case sharedBy:
                if(!StringUtils.isEmpty(value.getSharedBy())){
                    SearchQuery searchQuery = new NativeSearchQueryBuilder()
                            .withQuery(matchQuery("sharedBy",value.getSharedBy()))
                            .build();
                    filteredArticles.addAll(elasticsearchConfig.elasticsearchTemplate().queryForList(searchQuery, Article.class));
                } else {
                    throw new GraphqlServerSideException("Unsupported value. In order to filter by sharedBy, value must be a String");
                }
                break;

            case tag:
                if(!StringUtils.isEmpty(value.getTag())){
                    QueryBuilder builder = boolQuery()
                            .must(matchAllQuery())
                            .filter(termQuery("tags", value.getTag()));
                    SearchQuery searchQuery = new NativeSearchQueryBuilder()
                            .withQuery(builder)
                            .build();
                    filteredArticles.addAll(elasticsearchConfig.elasticsearchTemplate().queryForList(searchQuery, Article.class));
                } else {
                    throw new GraphqlServerSideException("Unsupported value. In order to filter by tag, value must be a String");
                }
                break;

            case suggestedTag:
                if(!StringUtils.isEmpty(value.getSuggestedTag())){
                    QueryBuilder builder = boolQuery()
                            .must(matchAllQuery())
                            .filter(termQuery("suggestedTags", value.getSuggestedTag()));
                    SearchQuery searchQuery = new NativeSearchQueryBuilder()
                            .withQuery(builder)
                            .build();
                    filteredArticles.addAll(elasticsearchConfig.elasticsearchTemplate().queryForList(searchQuery, Article.class));
                } else {
                    throw new GraphqlServerSideException("Unsupported value. In order to filter by suggestedTag, value must be a String");
                }
                break;

            default:
                throw new GraphqlServerSideException("Unsupported filter: " + filter + "Filter must be : " + Arrays.toString(FilterArticleEnum.values()));
        }
        filteredArticles.sort(Comparator.comparing(Article::getId));
        return filteredArticles;
    }
}
