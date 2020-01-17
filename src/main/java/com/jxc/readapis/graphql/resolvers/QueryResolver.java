package com.jxc.readapis.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jxc.readapis.dto.UserInfosDTO;
import com.jxc.readapis.graphql.exceptions.GraphqlServerSideException;
import com.jxc.readapis.graphql.models.FilterArticleInput;
import com.jxc.readapis.graphql.models.FilterUserInput;
import com.jxc.readapis.graphql.models.enums.FilterArticleEnum;
import com.jxc.readapis.graphql.models.enums.FilterUserEnum;
import com.jxc.readapis.mappers.UserMapper;
import fr.esir.jxc.domain.models.Address;
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

    public List<UserInfosDTO> findAllUsers(){
        List<UserInfosDTO> userInfosDTOS = new ArrayList<>();
        userRepository.findAll().getContent().forEach(user -> userInfosDTOS.add(UserMapper.convertToUserInfosDTO(user)));
        return userInfosDTOS;
    }

    public List<UserInfosDTO> findAllUsersFilterBy(FilterUserEnum filter, FilterUserInput value) {
        List<UserInfosDTO> filteredUsers;
        switch (filter) {
            case EMAIL:
                filteredUsers = userEmailQuery(value.getEmail());
                break;

            case USERNAME:
                filteredUsers = userUsernameQuery(value.getUsername());
                break;

            case ADDRESS:
                filteredUsers = userAddressQuery(value.getAddress());
                break;

            case FRIEND:
                filteredUsers = userFriendQuery(value.getFriend());
                break;

            default:
                throw new GraphqlServerSideException("Unsupported filter: " + filter + "Filter must be : " + Arrays.toString(FilterUserEnum.values()));
        }
        filteredUsers.sort(Comparator.comparing(UserInfosDTO::getEmail));
        return filteredUsers;
    }

    /*----------------------- ARTICLE -----------------------*/

    public List<Article> findAllArticles(){
        return articleRepository.findAll().getContent();
    }

    public List<Article> findAllArticlesFilterBy(FilterArticleEnum filter, FilterArticleInput value) {
        List<Article> filteredArticles;
        switch (filter) {
            case ID:
                filteredArticles = articleIdQuery(value.getId());
                break;

            case URL:
                filteredArticles = articleUrlQuery(value.getUrl());
                break;

            case OWNER:
                filteredArticles = articleOwnerQuery(value.getOwner());
                break;

            case SHARED_BY:
                filteredArticles = articleSharedByQuery(value.getSharedBy());
                break;

            case TAG:
                filteredArticles = articleTagQuery(value.getTag());
                break;

            case SUGGESTED_TAG:
                filteredArticles = articleSuggestedTagQuery(value.getSuggestedTag());
                break;

            default:
                throw new GraphqlServerSideException("Unsupported filter: " + filter + "Filter must be : " + Arrays.toString(FilterArticleEnum.values()));
        }
        filteredArticles.sort(Comparator.comparing(Article::getId));
        return filteredArticles;
    }

    private List<UserInfosDTO> userEmailQuery(String email){
        List<UserInfosDTO> filteredUsers = new ArrayList<>();
        if (!StringUtils.isEmpty(email)) {
            Optional<User> optionalUser = userRepository.findById(email.toLowerCase());
            optionalUser.ifPresent(user -> filteredUsers.add(UserMapper.convertToUserInfosDTO(user)));
        } else {
            throw new GraphqlServerSideException("Unsupported value. In order to filter by email, value must be a String");
        }
        return filteredUsers;
    }

    private List<UserInfosDTO> userUsernameQuery(String username) {
        List<UserInfosDTO> filteredUsers = new ArrayList<>();
        if (!StringUtils.isEmpty(username)) {
            SearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(matchQuery("username", username.toLowerCase()))
                    .build();
            elasticsearchConfig.elasticsearchTemplate().queryForList(searchQuery, User.class).forEach(user ->
                    filteredUsers.add(UserMapper.convertToUserInfosDTO(user)));
        } else {
            throw new GraphqlServerSideException("Unsupported value. In order to filter by username, value must be a String");
        }
        return filteredUsers;
    }

    private List<UserInfosDTO> userAddressQuery(Address address){
        List<UserInfosDTO> filteredUsers = new ArrayList<>();
        if (address != null && !StringUtils.isEmpty(address.getPostalCode()) && !StringUtils.isEmpty(address.getStreet()) && !StringUtils.isEmpty(address.getComplement()) && address.getStreetNumber() != 0) {
            QueryBuilder builder = boolQuery()
                    .must(matchAllQuery())
                    .filter(matchQuery("address.postalCode", address.getPostalCode().toLowerCase()))
                    .filter(matchQuery("address.street", address.getStreet().toLowerCase()))
                    .filter(matchQuery("address.streetNumber", address.getStreetNumber()))
                    .filter(matchQuery("address.complement", address.getComplement().toLowerCase()));
            SearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(builder)
                    .build();
            elasticsearchConfig.elasticsearchTemplate().queryForList(searchQuery, User.class).forEach(user ->
                    filteredUsers.add(UserMapper.convertToUserInfosDTO(user)));
        } else {
            throw new GraphqlServerSideException("Unsupported value. In order to filter by address, value must be an Address");
        }
        return filteredUsers;
    }

    private List<UserInfosDTO> userFriendQuery(String friend){
        List<UserInfosDTO> filteredUsers = new ArrayList<>();
        if (!StringUtils.isEmpty(friend)) {
            QueryBuilder builder = boolQuery()
                    .must(matchAllQuery())
                    .filter(termQuery("friends", friend.toLowerCase()));
            SearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(builder)
                    .build();
            elasticsearchConfig.elasticsearchTemplate().queryForList(searchQuery, User.class).forEach(user ->
                    filteredUsers.add(UserMapper.convertToUserInfosDTO(user)));
        } else {
            throw new GraphqlServerSideException("Unsupported value. In order to filter by friend, value must be a String");
        }
        return filteredUsers;
    }

    private List<Article> articleIdQuery(String id){
        List<Article> filteredArticles = new ArrayList<>();
        if(!StringUtils.isEmpty(id)){
            Optional<Article> article = articleRepository.findById(id.toLowerCase());
            article.ifPresent(filteredArticles::add);
        } else {
            throw new GraphqlServerSideException("Unsupported value. In order to filter by id, value must be a String");
        }
        return filteredArticles;
    }

    private List<Article> articleUrlQuery(String url){
        List<Article> filteredArticles = new ArrayList<>();
        if(!StringUtils.isEmpty(url)){
            SearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(matchQuery("url", url.toLowerCase()))
                    .build();
            filteredArticles.addAll(elasticsearchConfig.elasticsearchTemplate().queryForList(searchQuery, Article.class));
        } else {
            throw new GraphqlServerSideException("Unsupported value. In order to filter by url, value must be a String");
        }
        return filteredArticles;
    }

    private List<Article> articleOwnerQuery(String owner){
        List<Article> filteredArticles = new ArrayList<>();
        if(!StringUtils.isEmpty(owner)){
            SearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(matchQuery("owner", owner.toLowerCase()))
                    .build();
            filteredArticles.addAll(elasticsearchConfig.elasticsearchTemplate().queryForList(searchQuery, Article.class));
        } else {
            throw new GraphqlServerSideException("Unsupported value. In order to filter by owner, value must be a String");
        }
        return filteredArticles;
    }

    private List<Article> articleSharedByQuery(String sharedBy){
        List<Article> filteredArticles = new ArrayList<>();
        if(!StringUtils.isEmpty(sharedBy)){
            SearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(matchQuery("sharedBy", sharedBy.toLowerCase()))
                    .build();
            filteredArticles.addAll(elasticsearchConfig.elasticsearchTemplate().queryForList(searchQuery, Article.class));
        } else {
            throw new GraphqlServerSideException("Unsupported value. In order to filter by sharedBy, value must be a String");
        }
        return filteredArticles;
    }

    private List<Article> articleTagQuery(String tag){
        List<Article> filteredArticles = new ArrayList<>();
        if(!StringUtils.isEmpty(tag)){
            QueryBuilder builder = boolQuery()
                    .must(matchAllQuery())
                    .filter(termQuery("tags", tag.toLowerCase()));
            SearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(builder)
                    .build();
            filteredArticles.addAll(elasticsearchConfig.elasticsearchTemplate().queryForList(searchQuery, Article.class));
        } else {
            throw new GraphqlServerSideException("Unsupported value. In order to filter by tag, value must be a String");
        }
        return filteredArticles;
    }

    private List<Article> articleSuggestedTagQuery(String suggestedTag){
        List<Article> filteredArticles = new ArrayList<>();
        if(!StringUtils.isEmpty(suggestedTag)){
            QueryBuilder builder = boolQuery()
                    .must(matchAllQuery())
                    .filter(termQuery("suggestedTags", suggestedTag.toLowerCase()));
            SearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(builder)
                    .build();
            filteredArticles.addAll(elasticsearchConfig.elasticsearchTemplate().queryForList(searchQuery, Article.class));
        } else {
            throw new GraphqlServerSideException("Unsupported value. In order to filter by suggestedTag, value must be a String");
        }
        return filteredArticles;
    }
}
