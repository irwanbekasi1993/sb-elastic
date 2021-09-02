package sb.elastic.sbelastic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import sb.elastic.sbelastic.model.Customer;

@Repository
public interface CustomerRepository extends ElasticsearchRepository<Customer,Long>{
    
}
