package telran.java52.accouting.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java52.accouting.model.UserAccount;

public interface UserAccountRepository extends MongoRepository<UserAccount, String> {

}
