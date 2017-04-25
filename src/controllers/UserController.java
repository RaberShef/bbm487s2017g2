package controllers;

import models.BookModel;
import models.UserModel;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class UserController {
	private static UserModel currentUser;
		
	public static UserModel getUserModel(){
		return currentUser;
	}
	
	public static int login(String inputUsername, String inputPassword){

		Connection con=null;
		Statement st=null;
		ResultSet rsMember=null;
		ResultSet rsLibrarian=null;
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
		int isLibrarian=-1;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			
			st=con.createStatement();
			
			rsLibrarian = st.executeQuery("select *from librarian");
			
			while(rsLibrarian.next()){
				if(rsLibrarian.getString("username").equals(inputUsername) && rsLibrarian.getString("password").equals(inputPassword)){
					isLibrarian=1;
					currentUser = new UserModel(rsLibrarian.getInt("librarian_id"),rsLibrarian.getString("username"), rsLibrarian.getString("password"), rsLibrarian.getString("first_name"), rsLibrarian.getString("last_name"), "deneme@gmail.com", true);
					break;
				}
			}
			rsLibrarian.close();
			
			rsMember=st.executeQuery("select *from member");//gets all records in member table.
			if(isLibrarian != 1){
				while(rsMember.next()){
					if(rsMember.getString("username").equals(inputUsername)&& rsMember.getString("password").equals(inputPassword)){
						isLibrarian=0;
						currentUser = new UserModel(rsMember.getInt("member_id"),rsMember.getString("username"), rsMember.getString("password"), rsMember.getString("first_name"), rsMember.getString("last_name"), rsMember.getString("email"), false);
						break;
					}
				}
			}
			rsMember.close();
		}
		catch(Exception e){
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return isLibrarian;
	}
	
	public static void getLoanedBooksByCondition(String condition,String value, JList<BookModel> list){
		Connection con=null;
		PreparedStatement stBook=null;
		PreparedStatement stViewBooks=null;
		ResultSet rsBook=null;
		ResultSet rsViewBooks=null;
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
					
			stBook=con.prepareStatement(sql);
			rsBook=stBook.executeQuery();
			
			stViewBooks=con.prepareStatement("select *from view_books where member_id='"+currentUser.getId()+"'");
			rsViewBooks=stViewBooks.executeQuery();
			
			DefaultListModel<BookModel> listModel=new DefaultListModel<BookModel>();
			
			while(rsViewBooks.next()){
				while(rsBook.next()){
					if(rsBook.getInt("barcode_no")==rsViewBooks.getInt("barcode_no")){
						BookModel book = new BookModel(rsBook.getInt("barcode_no"),rsBook.getString("name"),rsBook.getString("author_name"),rsBook.getInt("printing_year"),rsBook.getInt("number_of_pages"),rsBook.getInt("count"));
						listModel.addElement(book);
						break;
					}
				}
				rsBook.beforeFirst();	
			}
			list.setModel(listModel);
			
			rsBook.close();
			rsViewBooks.close();
		}
		catch(Exception e){
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static void returnBook(BookModel selectedBook){
		Connection con=null;
		PreparedStatement st=null;
		
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			
			st=con.prepareStatement("delete from view_books where barcode_no = ? and member_id = ?");
			st.setInt(1,selectedBook.getBarcode_no());
			st.setInt(2,currentUser.getId());
		    st.executeUpdate();
			st.close();
		}
		catch(Exception e){
			try {
				e.printStackTrace();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static int checkoutBook(BookModel selectedBook){
		Connection con=null;
		PreparedStatement st=null;
		
		
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
				
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			st=con.prepareStatement("insert into view_books values(?,?,?,?)");
			st.setInt(1,selectedBook.getBarcode_no());
			st.setInt(2,currentUser.getId());
			st.setInt(3,0);
			
			st.setString(4,dateFormat.format(date));
		    st.executeUpdate();	
		 
			st.close();
		}
		catch(Exception e){
			try {
				//e.printStackTrace();
				con.close();
				return -1;
				
			} catch (SQLException e1) {
				//e1.printStackTrace();
			}
		}
		return 0;
	}
	
	public static void getFinedBooks(JList<BookModel> list){
		
	}
	
	public static void updateFinedBooks(JList<BookModel> list){
		
	}
	
	public static void deleteMember(int userId){
		
	}
	
	public static boolean updateMember(UserModel member){
		return true;
	}
	
	public static boolean createMember(UserModel member){
		return true;
	}
	
}
