package org.bgu.domain.repository;

import org.bgu.domain.model.User;

/**
 * @author William Gentry
 */
public interface UserRepository {

	User findUserByEmail(String email);
	User saveUser(User user);
}
