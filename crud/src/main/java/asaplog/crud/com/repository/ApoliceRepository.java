package asaplog.crud.com.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import asaplog.crud.com.model.Apolice;

public interface ApoliceRepository extends MongoRepository<Apolice, String> {

}
