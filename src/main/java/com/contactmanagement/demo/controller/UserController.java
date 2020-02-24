package com.contactmanagement.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.contactmanagement.demo.model.User;
import com.contactmanagement.demo.service.UserService;
import com.contactmanagement.status.ResponseStatus;
import com.contactmanagement.util.ContactManagementConstants;

@CrossOrigin
@RestController
@RequestMapping(path = "/user")
public class UserController extends GenericController<User> {
	@Autowired
	private UserService userService;

	@GetMapping(path = "/checkUserName")
	public @ResponseBody boolean isUserNamePresent(@RequestParam String userName) {
		return userService.isUserNamePresent(userName);
	}

	@Override
	@PostMapping(path = "/save", produces = { "application/json" })
	public @ResponseBody HashMap<String, Object> save(@RequestBody User dataObject) {
		if (!ContactManagementConstants.emailValidator(dataObject.getEmailAddress()))
			return new ResponseStatus(ContactManagementConstants.getErrorStatus, ContactManagementConstants.getInvalidEmailError).getResponseData();
		else if (!ContactManagementConstants.phoneNumberValidator(dataObject.getPhoneNo()))
			return new ResponseStatus(ContactManagementConstants.getErrorStatus, ContactManagementConstants.getInvalidPhoneNumberError).getResponseData();
		else if (dataObject.getUserName().length() < ContactManagementConstants.minimumTextRequire)
			return new ResponseStatus(ContactManagementConstants.getErrorStatus, ContactManagementConstants.getMinimumTextUserNameError).getResponseData();
		else if (dataObject.getUserid() == 0 && userService.isUserNamePresent(dataObject.getUserName()))
			return new ResponseStatus(ContactManagementConstants.getErrorStatus, ContactManagementConstants.getUserNamePrecentError).getResponseData();
		else if (!ContactManagementConstants.checkPasswordCriteria(dataObject.getPassword()))
			return new ResponseStatus(ContactManagementConstants.getErrorStatus, ContactManagementConstants.getInvalidPasswordCriteriaError).getResponseData();
		return super.save(dataObject);
	}

	@GetMapping(path = "/setAsFavouriteUnFavourite", produces = { "application/json" })
	public @ResponseBody HashMap<String, Object> setFavouriteUnFavourite(@RequestParam int userId, @RequestParam boolean isFavourite) {
		return userService.setFavouriteUnFavourite(userId, isFavourite).getResponseData();
	}

	@GetMapping(path = "/searchContact", produces = { "application/json" })
	public @ResponseBody HashMap<String, Object> searchContact(@RequestParam String searchText) {
		return userService.searchContact(searchText).getResponseData();
	}
}
