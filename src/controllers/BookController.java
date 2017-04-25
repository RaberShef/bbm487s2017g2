package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import models.BookModel;

public class BookController {
	
	public static void getBookByCondition(String condition,String value, JList<BookModel> list){
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			String parameter="";
			if(condition.equals("Author")){
				parameter="author_name = '"+value+"'";
			}
			else if(condition.equals("Name")){
				parameter="name = '"+value+"'";
			}
			else if(condition.equals("Barcode")){
				parameter="barcode_no = '"+Integer.parseInt(value)+"'";
			}
			String sql="select *from book";
			if (value!=null&& value.trim().length()>0){
				sql+=" where "+parameter;
			}
			
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			DefaultListModel<BookModel> listModel=new DefaultListModel<BookModel>();
			while(rs.next()){
				BookModel book = new BookModel(rs.getInt("barcode_no"),rs.getString("name"),
						rs.getString("author_name"),rs.getInt("printing_year"),
						rs.getInt("number_of_pages"),rs.getInt("count"));
				listModel.addElement(book);
			}
			list.setModel(listModel);
			
			rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static boolean checkStock(BookModel bookModel){
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			
			st=con.prepareStatement("select count(*) from view_books where barcode_no = '"+bookModel.getBarcode_no()+"'");
			rs=st.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			
			rs.close();
			
			if(bookModel.getCount()>count){
				return true;
			}
			else{
				return false;
			}
		}
		catch(Exception e){
			try {
				con.close();
			} catch (SQLException e1) {
			
				e1.printStackTrace();
			}
		}
		return false;
	}
	
	public static boolean updateBook(BookModel book){
		return true;
	}
	
	public static boolean createBook(BookModel book){
		return true;
	}
	
	public static boolean deleteBook(BookModel book){
		return true;
	}
}
