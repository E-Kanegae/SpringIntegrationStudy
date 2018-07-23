package org.ek.sample.service;

import java.util.Map;

import org.ek.sample.domain.User;

public interface UserService {
	
	User findUser(User user);
	
	User findUserByUsername(User user);
	
	Map<String, Object> findUserWithUsernameInMap(Map<String, Object> userdata);

}
