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
	
	public static int getFinedBooks(JList<BookModel> list, JList<Integer> list2){
		int totalFine = 0;
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
	
			String sql="select b.name,b.author_name,b.barcode_no,b.printing_year,b.number_of_pages,b.count,vb.penalty_point from book b,view_books vb "
					+ "where b.barcode_no=vb.barcode_no and vb.member_id='"+UserController.getUserModel().getId()+"' and vb.penalty_point>0 ";
			
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			DefaultListModel<BookModel> listModel=new DefaultListModel<BookModel>();
			DefaultListModel<Integer> listModel2=new DefaultListModel<Integer>();
			while(rs.next()){
				BookModel book = new BookModel(rs.getInt("barcode_no"),rs.getString("name"),
						rs.getString("author_name"),rs.getInt("printing_year"),
						rs.getInt("number_of_pages"),rs.getInt("count"));
				listModel.addElement(book);
				listModel2.addElement(rs.getInt("penalty_point"));
				totalFine += rs.getInt("penalty_point");
			}
			list.setModel(listModel);
			list2.setModel(listModel2);
			
			rs.close();
			return totalFine;
		}
		catch(Exception e){
			e.printStackTrace();
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return -1;
	}
	
	public static void updateBook(BookModel bookModel){
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			String sql="update  book set name = ?, author_name = ?, printing_year =?, number_of_pages =?, count =? where barcode_no =?";
			st=con.prepareStatement(sql);
			st.setString(1,bookModel.getName());
			st.setString(2,bookModel.getAuthor_name());
			st.setString(3,String.valueOf(bookModel.getPrinting_year()));
			st.setInt(4,bookModel.getNumber_of_pages());
			st.setInt(5,bookModel.getCount());
			st.setInt(6,bookModel.getBarcode_no());
			st.executeUpdate();
		    st.close();
		    
		}
		catch(Exception e){
			try {
				con.close();
			} catch (SQLException e1) {
			
				e1.printStackTrace();
			}
		}
		
	}
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
	public static void deleteBook(BookModel bookModel){
		
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			st=con.prepareStatement("delete from book where barcode_no = '"+bookModel.getBarcode_no()+"'");
			st.executeUpdate();
		    st.close();
		    
		}
		catch(Exception e){
			try {
				con.close();
			} catch (SQLException e1) {
			
				e1.printStackTrace();
			}
		}
		
		
	}
	
	public static  void createNewBook(BookModel bookModel){
		
		
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			String sql="insert into book values (DEFAULT,?,?,?,?,?)";
			st=con.prepareStatement(sql);
			st.setString(1,bookModel.getName());
			st.setString(2,bookModel.getAuthor_name());
			st.setString(3,String.valueOf(bookModel.getPrinting_year()));
			st.setInt(4,bookModel.getNumber_of_pages());
			st.setInt(5,bookModel.getCount());
			st.executeUpdate();
		    st.close();
		    
		}
		catch(Exception e){
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
}
