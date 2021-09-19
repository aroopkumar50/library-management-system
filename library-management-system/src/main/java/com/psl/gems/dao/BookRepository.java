package com.psl.gems.dao;

import org.springframework.data.repository.CrudRepository;

import com.psl.gems.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
