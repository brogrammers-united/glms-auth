package org.bgu.domain.repository;

import org.bgu.domain.model.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @author William Gentry
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

	private final MongoTemplate template;
	private final Update updateUserQuery = new Update();

	public UserRepositoryImpl(MongoTemplate template) {
		this.template = template;
	}

	@Override
	public User findUserByEmail(String email) {
		return template.findOne(Query.query(Criteria.where("email").is(email)), User.class);
	}

	@Override
	public User saveUser(User user) {
		return template.save(user);
	}
}
