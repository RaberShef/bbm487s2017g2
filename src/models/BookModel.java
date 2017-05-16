package models;

public class BookModel {
	private int barcode_no;
	private String name;
	private String author_name;
	private int printing_year;
	private int number_of_pages;
	private int count;
	
	public BookModel(int barcode_no, String name, String author_name, int printing_year, int number_of_pages, int count) {
        this.barcode_no = barcode_no;
    	this.name = name;
        this.author_name = author_name;
        this.printing_year = printing_year;
        this.number_of_pages = number_of_pages;
        this.count = count;
    }
	
	public String toString(){
		return name + ",  " + author_name + ",  " + barcode_no+", "+number_of_pages+" , "+printing_year;
	}
	
	public int getBarcode_no() {
		return barcode_no;
	}
	public void setBarcode_no(int barcode_no) {
		this.barcode_no = barcode_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public int getPrinting_year() {
		return printing_year;
	}
	public void setPrinting_year(int printing_year) {
		this.printing_year = printing_year;
	}
	public int getNumber_of_pages() {
		return number_of_pages;
	}
	public void setNumber_of_pages(int number_of_pages) {
		this.number_of_pages = number_of_pages;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
