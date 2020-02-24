package com.contactmanagement.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contactmanagement.demo.model.User;
import com.contactmanagement.demo.repository.UserRepository;
import com.contactmanagement.status.ResponseStatus;
import com.contactmanagement.util.ContactManagementConstants;

@Service
public class UserService extends GenericService<User> {

	@Autowired
	private UserRepository userRepository;

	public Iterable<User> getAllUser() {
		return userRepository.findAll();
	}

	public boolean isUserNamePresent(String userName) {
		return userRepository.isUserNamePresent(userName) > 0 ? true : false;
	}

	public ResponseStatus setFavouriteUnFavourite(int userId, boolean isFavourite) {
		User user = userRepository.findById(userId).get();
        user.setIsFavourite((byte) (isFavourite?1:0));
        try {
        	User data = save(user);
            return new ResponseStatus(ContactManagementConstants.getSuccessStatus,data , ContactManagementConstants.updateMessage) ;
		} catch (Exception e) {
			return  new ResponseStatus(ContactManagementConstants.getErrorStatus,ContactManagementConstants.ErrorInUpdateMessage );
		}
	}

	public ResponseStatus searchContact(String searchText) {
		try {
			List<User> users=userRepository.searchContact(searchText);
            return new ResponseStatus(ContactManagementConstants.getSuccessStatus,users , ContactManagementConstants.dataFetchMessage) ;
    	}catch (Exception e) {
			return  new ResponseStatus(ContactManagementConstants.getErrorStatus,ContactManagementConstants.ErrorInSearchMessage);
		}
	}
}
