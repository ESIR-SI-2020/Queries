package com.jxc.readapis.grpc.services;

import com.jxc.readapis.grpc.Article;
import com.jxc.readapis.grpc.ArticleList;
import com.jxc.readapis.grpc.ArticleRequestId;
import com.jxc.readapis.grpc.ArticleRequestOwner;
import com.jxc.readapis.grpc.generated.ArticleServiceGrpc;
import com.jxc.readapis.grpc.mappers.ArticleMapper;
import fr.esir.jxc.elasticsearch.config.ElasticsearchConfig;
import fr.esir.jxc.elasticsearch.repositories.ArticleRepository;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@GRpcService
public class ArticleServiceImpl extends ArticleServiceGrpc.ArticleServiceImplBase{

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ElasticsearchConfig elasticsearchConfig;

    @Override
    public void findArticleByID(ArticleRequestId request, StreamObserver<Article> responseObserver) {
        Optional<fr.esir.jxc.domain.models.Article> optionalArticle = articleRepository.findById(request.getId());
        Article response = Article.newBuilder().build();
        if(optionalArticle.isPresent()){
            response = ArticleMapper.convertToArticle(optionalArticle.get());
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findArticlesByOwner(ArticleRequestOwner request, StreamObserver<ArticleList> responseObserver) {
        List<Article> response = new ArrayList<>();
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("owner", request.getOwner().toLowerCase()))
                .build();
        List<fr.esir.jxc.domain.models.Article> articles = elasticsearchConfig.elasticsearchTemplate().queryForList(searchQuery, fr.esir.jxc.domain.models.Article.class);
        articles.forEach(article -> response.add(ArticleMapper.convertToArticle(article)));
        ArticleList articleList = ArticleList.newBuilder().addAllArticle(response).build();
        responseObserver.onNext(articleList);
        responseObserver.onCompleted();
    }
}
