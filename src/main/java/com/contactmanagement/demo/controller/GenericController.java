package com.contactmanagement.demo.controller;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contactmanagement.demo.service.GenericService;
import com.contactmanagement.status.ResponseStatus;
import com.contactmanagement.util.ContactManagementConstants;

public abstract class GenericController<T> {

	@Autowired
	private GenericService<T> genericService;

	@GetMapping(path = "/", produces = { "application/json" })
	public @ResponseBody HashMap<String, Object> findAll() {
		return new ResponseStatus(ContactManagementConstants.getSuccessStatus, genericService.findAll(), ContactManagementConstants.dataFetchMessage).getResponseData();
	}

	@PostMapping(path = "/save", produces = { "application/json" })
	public @ResponseBody HashMap<String, Object> save(@RequestBody T dataObject) {
		long objectId = 0;
		try {
			dataObject = genericService.save(dataObject);
			Method method = dataObject.getClass().getMethod(ContactManagementConstants.getEntityPrimaryKey.get(dataObject.getClass().getSimpleName()));
			objectId = ((Integer) method.invoke(dataObject)).longValue();
			if (objectId != 0) {
				return new ResponseStatus(ContactManagementConstants.getSuccessStatus, dataObject, ContactManagementConstants.saveMessage).getResponseData();
			} else {
				return new ResponseStatus(ContactManagementConstants.getErrorStatus, ContactManagementConstants.ErrorInSaveMessage).getResponseData();
			}
		} catch (Exception e) {
			return new ResponseStatus(ContactManagementConstants.getErrorStatus, ContactManagementConstants.ErrorInSaveMessage).getResponseData();
		}
	}

	@PostMapping(path = "/update", produces = { "application/json" })
	public @ResponseBody HashMap<String, String> update(@RequestBody T dataObject) {
		ResponseStatus response;
		long objectId = 0;
		try {
			genericService.save(dataObject);
			Method method = dataObject.getClass().getMethod(ContactManagementConstants.getEntityPrimaryKey.get(dataObject.getClass().getSimpleName()));
			objectId = ((Integer) method.invoke(dataObject)).longValue();
			if (objectId != 0) {
				response = new ResponseStatus(ContactManagementConstants.getSuccessStatus, dataObject, ContactManagementConstants.updateMessage);
			} else {
				response = new ResponseStatus(ContactManagementConstants.getErrorStatus, dataObject, ContactManagementConstants.ErrorInUpdateMessage);
			}
		} catch (Exception e) {
			response = new ResponseStatus(ContactManagementConstants.getErrorStatus, ContactManagementConstants.ErrorInUpdateMessage);
		}
		return response.getStatus();
	}

	@DeleteMapping(path = "/delete", produces = { "application/json" })
	public @ResponseBody HashMap<String, Object> delete(@RequestBody T dataObject) {
		ResponseStatus response;
		try {
			if (genericService.delete(dataObject)) {
				response = new ResponseStatus(ContactManagementConstants.getSuccessStatus, dataObject, ContactManagementConstants.deleteSuccessMessage);
			} else {
				response = new ResponseStatus(ContactManagementConstants.getErrorStatus, dataObject, ContactManagementConstants.deleteFailMessage);
			}
		} catch (Exception e) {
			response = new ResponseStatus(ContactManagementConstants.getErrorStatus, ContactManagementConstants.deleteFailMessage);
		}
		return response.getResponseData();
	}

}
