package com.psl.gems.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.psl.gems.model.Book;
import com.psl.gems.model.BookObj;

public interface BookObjRepository extends CrudRepository<BookObj, Integer> {
	
	@Query(value="SELECT b.* FROM book_obj b LEFT OUTER JOIN issue i ON b.id = i.book_obj_id WHERE b.book_isbn = ?1 AND (i.status IS NULL OR (i.status != 2 AND i.status != 0)) LIMIT 1", nativeQuery=true)
	BookObj findFirstAvailableCopyByBookId(long book_id);

	void deleteByBook(Book book);
}
