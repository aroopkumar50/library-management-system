package com.psl.gems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.gems.dao.BookObjRepository;
import com.psl.gems.model.Book;
import com.psl.gems.model.BookObj;

@Service
public class BookObjService {
	@Autowired
	BookObjRepository bookObjRepository;
	
	public BookObj getAvailableCopyByBookId(long book_id) {
		return bookObjRepository.findFirstAvailableCopyByBookId(book_id);
	}
	
	public BookObj save(BookObj bookObj) {
		return bookObjRepository.save(bookObj);
	}
	
	public List<BookObj> findAll() {
		return (List<BookObj>) bookObjRepository.findAll();
	}
	
	public BookObj findById(int id) {
		return bookObjRepository.findById(id).get();
	}
	
	public void delete(BookObj bookObj) {
		bookObjRepository.delete(bookObj);
	}
	
	public void deleteByBook(Book book) {
		bookObjRepository.deleteByBook(book);
	}
}
