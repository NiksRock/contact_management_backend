package com.contactmanagement.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.contactmanagement.demo.model.User;

@Repository
public interface UserRepository  extends GenericRepository<User>  {

	
	@Query(value="SELECT count(user.userid) from User user where user.userName=:userName")
	int isUserNamePresent(@Param("userName")String userName);

	
	@Query("SELECT user from User user WHERE user.emailAddress LIKE %:searchText%")
	List<User> searchContact(@Param("searchText") String searchText);
}
