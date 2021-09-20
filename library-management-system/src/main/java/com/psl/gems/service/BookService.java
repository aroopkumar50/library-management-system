package com.psl.gems.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.gems.dao.BookObjRepository;
import com.psl.gems.dao.BookRepository;
import com.psl.gems.dao.UserRepository;
import com.psl.gems.model.Book;

@Service
public class BookService 
{
	@Autowired
	BookRepository bookRepo;
	
	@Autowired
	BookObjRepository bookObjRepo;
	
	@Autowired
	UserRepository usRepo;
	
	public void save(Book book)
	{
		bookRepo.save(book);
	}
	
	/*public void saveById(long ISBN)
	{
		bookObjRepo.save(bookRepo.findById(ISBN).get());
	}*/
	
	public List<Book> findAll()
	{
		return (List<Book>) bookRepo.findAll();
	}
	
	public Book findById(long ISBN)
	{
		Book book = bookRepo.findById(ISBN).get();
		return book;
	}
	
	public List<Book> getByTitle(String title){
		List<Book> books = new ArrayList<>();
		for (Book book : bookRepo.findAll()) {
			if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
				books.add(book);
			}
		}
		return books;
	}
	
	public List<Book> getByAuthor(String author){
		List<Book> books = new ArrayList<>();
		for (Book book : bookRepo.findAll()) {
			if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
				books.add(book);
			}
		}	
		return books;
	}
	
	public List<Book> getByTitleAndAuthor(String title, String author){
		List<Book> books = new ArrayList<>();
		for (Book book : bookRepo.findAll()) {
			if (book.getTitle().toLowerCase().contains(title.toLowerCase()) &&
				book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
				books.add(book);
			}
		}
		return books;
	}
		
	public List<Book> searchBooks(String title, String author){
			
			List<Book> searchedBooks = new ArrayList<Book>();
			
			if (title != null && author == null) {
				searchedBooks = getByTitle(title);
			} else if (title == null && author != null) {
				searchedBooks = getByAuthor(author);
			} else if (title != null && author != null) {
				searchedBooks = getByTitleAndAuthor(title, author);
			} 
			
			return searchedBooks;
		}
	
	public void deleteById(long bookId) {
		bookRepo.deleteById(bookId);
	}
	
//	public List<Book> getUnprocessedBookReservations(){
//		List<Book> unprocessedBookReservations = new ArrayList<Book>();
//		for (Book book : bookRepo.findAll()) {
//			if (book.getReservedByUser() != null && book.getReadyForPickUp() == false) {
//				unprocessedBookReservations.add(book);
//			}
//		}
//		return unprocessedBookReservations;
//	}
//	
//	public List<Book> getProcessedBookReservations(){
//		List<Book> processedBookReservations = new ArrayList<Book>();
//		for (Book book : bookRepo.findAll()) {
//			if (book.getReservedByUser() != null && book.getReadyForPickUp() == true) { 
//				processedBookReservations.add(book);
//			}
//		}
//		return processedBookReservations;
//	}
}
