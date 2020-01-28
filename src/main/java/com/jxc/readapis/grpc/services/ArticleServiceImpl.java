package com.jxc.readapis.grpc.services;

import com.jxc.readapis.grpc.Article;
import com.jxc.readapis.grpc.ArticleRequest;
import com.jxc.readapis.grpc.generated.ArticleServiceGrpc;
import com.jxc.readapis.grpc.mappers.ArticleMapper;
import fr.esir.jxc.elasticsearch.repositories.ArticleRepository;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@GRpcService
public class ArticleServiceImpl extends ArticleServiceGrpc.ArticleServiceImplBase{

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public void findArticleByID(ArticleRequest request, StreamObserver<Article> responseObserver) {
        Optional<fr.esir.jxc.domain.models.Article> optionalArticle = articleRepository.findById(request.getId());
        Article response = Article.newBuilder().build();
        if(optionalArticle.isPresent()){
            response = ArticleMapper.convertToArticle(optionalArticle.get());
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
