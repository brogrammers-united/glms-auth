package org.bgu.domain.repository;

import org.bgu.domain.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author William Gentry
 */
@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

	User findUserByEmail(String email);
}
