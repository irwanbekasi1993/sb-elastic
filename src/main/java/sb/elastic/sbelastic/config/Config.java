package sb.elastic.sbelastic.config;

import java.net.InetAddress;
import java.util.List;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories
public class Config extends AbstractElasticsearchConfiguration{

    @Value("${sbelastic.uri}")
    private String uri;

    @Value("${spring.data.elasticsearch.client.reactive.connection-timeout}")
    private long connectionTimeout;

    @Value("${spring.data.elasticsearch.client.reactive.socket-timeout}")
    private long socketTimeout;

    private ElasticsearchOperations operations;



    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        // TODO Auto-generated method stub
        
        ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo(uri).withSocketTimeout(socketTimeout).withConnectTimeout(connectionTimeout).build();

        return RestClients.create(clientConfiguration).rest();
    }

    
    
}
