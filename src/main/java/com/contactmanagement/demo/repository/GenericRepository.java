package com.contactmanagement.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T> extends CrudRepository<T, Integer> {

	Optional<T> findById(int id);
}