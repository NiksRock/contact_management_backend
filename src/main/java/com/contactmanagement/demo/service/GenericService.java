package com.contactmanagement.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.contactmanagement.demo.repository.GenericRepository;

public abstract class GenericService<T> implements Cloneable {
	
	@Autowired
	private GenericRepository<T> genericRepository;
	
	public T findAll() {
		return (T) genericRepository.findAll();
	}
	
	public T save(T dataObject) {
		return  genericRepository.save(dataObject);
	}

	public boolean delete(T dataObject) {	
		try{		
			genericRepository.delete(dataObject);
		}
		catch(Exception ex){
			return false;
		}
		return true;
	}


	
	
}	
