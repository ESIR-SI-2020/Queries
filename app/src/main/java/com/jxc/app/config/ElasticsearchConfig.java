package com.jxc.app.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.jxc.app.repositories")
public class ElasticsearchConfig {

    @Value("${elasticsearch.host:localhost}")
    private String host;

    @Value("${elasticsearch.port:9300}")
    private int port;

    @Value("${elasticsearch.clustername:pocket-cluster}")
    private String clusterName;

    @Value("${elasticsearch.home:D:/Programmes/elasticsearch/5.6.0}")
    private String home;

    @Bean
    public Client client() {
        Client client = null;
        try {
            final Settings elasticsearchSettings = Settings.builder()
                    .put("path.home", host)
                    .put("cluster.name", clusterName).build();
            TransportClient transportClient = new PreBuiltTransportClient(elasticsearchSettings);
            client = transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
    }
}
