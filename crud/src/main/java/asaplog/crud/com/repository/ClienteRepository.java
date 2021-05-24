package asaplog.crud.com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import asaplog.crud.com.model.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {
    
}
