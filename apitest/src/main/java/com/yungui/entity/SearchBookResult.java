package com.yungui.entity;

import java.util.List;

public class SearchBookResult {
	private int count;
	private List<Book> books;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	@Override
	public String toString() {
		return "SearchBookResult [count=" + count + ", books=" + books + "]";
	}
	
}
