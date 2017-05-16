package controllers;

import models.BookModel;
import models.UserModel;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class UserController {
	private static UserModel currentUser;
		
	public static UserModel getUserModel(){
		return currentUser;
	}
	
	public static boolean payFine(){
		Connection con=null;
		PreparedStatement st=null;
		
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date today = new Date();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			
			String sql="update view_books set penalty_point=0,reception_time='"+dateFormat.format(today) +"' where member_id='" +currentUser.getId() + "' and penalty_point>0";
			
			st=con.prepareStatement(sql);
			if(st.executeUpdate()>0){
				st.close();
				return true;
			}
			else{
				st.close();
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
	
	public static boolean checkIfFined(){
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			String sql="select *from view_books where member_id='" +currentUser.getId() + "' and penalty_point>0";
			
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			if(rs.next()){
				return true;
			}
		    st.close();
		    
		}
		catch(Exception e){
			try {
				con.close();
				return false;
			} catch (SQLException e1) {
			
				e1.printStackTrace();
				return false;
			}
		}
		
		return false;
	}
	
	public static boolean checkIfFined(BookModel selectedBook){
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			String sql="select *from view_books where member_id='" +currentUser.getId() + "' and penalty_point>0 and barcode_no ='"+selectedBook.getBarcode_no()+"'";
			
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			if(rs.next()){
				st.close();
				return true;
			}
			st.close();
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
	
	public static boolean addToWaitingList(int book_id){
		
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			String sql="insert into waiting_list values(?,?, null)";
			
			st=con.prepareStatement(sql);
			st.setInt(1,currentUser.getId());
			st.setInt(2,book_id);
			st.executeUpdate();
		    st.close();
		    
		}
		catch(Exception e){
			try {
				con.close();
				return false;
			} catch (SQLException e1) {
			
				e1.printStackTrace();
				return false;
			}
		}
		
		return true;
		
	}
	public static void updateUser(UserModel userModel){	
			Connection con=null;
			PreparedStatement st=null;
			ResultSet rs=null;
			
			String url="jdbc:mysql://localhost:3306/tutorial";
			String username="root";
			String password="umut";
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection(url,username,password);
				String sql="update  member set first_name = ?, last_name = ?, password =?, username =?, email =? where member_id =?";
				st=con.prepareStatement(sql);
				st.setString(1,userModel.getName());
				st.setString(2,userModel.getSurname());
				st.setString(3,userModel.getPassword());
				st.setString(4,userModel.getUsername());
				st.setString(5,userModel.getEmail());
				st.setInt(6,userModel.getId());
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
	public static void deleteUser(UserModel userModel){
			
			Connection con=null;
			PreparedStatement st=null;
			ResultSet rs=null;
			
			String url="jdbc:mysql://localhost:3306/tutorial";
			String username="root";
			String password="umut";
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection(url,username,password);
				st=con.prepareStatement("delete from  member  where member_id = '"+userModel.getId()+"'");
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
	public static  void createNewUser(UserModel userModel){
		
		
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			String sql="insert into member values (DEFAULT,?,?,?,?,?)";
			st=con.prepareStatement(sql);
			st.setString(1,userModel.getName());
			st.setString(2,userModel.getSurname());
			st.setString(3,userModel.getPassword());
			st.setString(4,userModel.getUsername());
			st.setString(5,userModel.getEmail());
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
	
	public static void getMemberByCondition(String condition,String value, JList<UserModel> list){
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
		//"Name", "Surname", "Username", "E-Mail"
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			String parameter="";
			if(condition.equals("Name")){
				parameter="first_name = '"+value+"'";
			}
			else if(condition.equals("Surname")){
				parameter="last_name = '"+value+"'";
			}
			else if(condition.equals("Username")){
				parameter="username = '"+value+"'";
			}
			
			else if(condition.equals("E-Mail")){
			  parameter="email = '"+value+"'";
			}
			String sql="select *from member";
			if (value!=null&& value.trim().length()>0){
				sql+=" where "+parameter;
			}
			
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			DefaultListModel<UserModel> listModel=new DefaultListModel<UserModel>();
			while(rs.next()){
				
				UserModel user=new UserModel(rs.getInt("member_id"),rs.getString("username"),rs.getString("password"), rs.getString("first_name"),rs.getString("last_name"),rs.getString("email"),false);
				listModel.addElement(user);
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
	
	public static boolean checkIfFirstInWList(int book_id){
		boolean isWaited=false;
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			
			st=con.prepareStatement("select *from waiting_list where barcode_no=?");
			st.setInt(1, book_id);
		    rs=st.executeQuery();
		    if(rs.next()){
		    	if(rs.getInt("member_id")==currentUser.getId()){
		    		isWaited = true;	
		    	}
		    	else{
		    		isWaited = false;
		    	}
		    	
		    }
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
		return isWaited;
	}
	
	public static boolean checkIfWaited(int book_id){
		boolean isWaited=false;
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			
			st=con.prepareStatement("select *from waiting_list where barcode_no=?");
			st.setInt(1, book_id);
		    rs=st.executeQuery();
		    if(rs.next()){
		    	isWaited = true;
		    }
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
		return isWaited;
	}
	
	public static void calculateFines(){
		Connection con=null;
		PreparedStatement st=null;
		PreparedStatement st2=null;
		ResultSet rs=null;
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date today = new Date();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			
			st=con.prepareStatement("select *from view_books");
			rs=st.executeQuery();
			while(rs.next()){
				String dateOfReception= rs.getString("reception_time");
				Date receptionDate = dateFormat.parse(dateOfReception);
				
				long diff = today.getTime() - receptionDate.getTime();
				int daysPassed = (int)(diff / (1000 * 60 * 60 * 24));
				if(daysPassed > 30){
					int fine = 5 * (daysPassed-30);
					st2=con.prepareStatement("update view_books set penalty_point =? where member_id = '"+rs.getInt("member_id")+"' and barcode_no = '"+rs.getInt("barcode_no")+"'");
					st2.setInt(1,fine);
					st2.executeUpdate();
					st2.close();
				}
			}
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
	
	public static void checkIfReservationExpired(){
		Connection con=null;
		PreparedStatement st=null;
		PreparedStatement st2=null;
		ResultSet rs=null;
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date today = new Date();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			
			st=con.prepareStatement("select *from waiting_list");
			rs=st.executeQuery();
			while(rs.next()){
				String dateOfReservation = rs.getString("date_of_reservation");
				if(dateOfReservation == null){
					continue;
				}
				Date reservationDate = dateFormat.parse(dateOfReservation);
				
				long diff = today.getTime() - reservationDate.getTime();
				int daysPassed = (int)(diff / (1000 * 60 * 60 * 24));
				if(daysPassed > 5){
					st2=con.prepareStatement("delete from waiting_list where member_id = '"+rs.getInt("member_id")+"' and barcode_no = '"+rs.getInt("barcode_no")+"'");
					st2.executeUpdate();
					st2.close();
				}
			}
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
	
	public static void returnBook(BookModel selectedBook){
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
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
			
			st=con.prepareStatement("select *from waiting_list where barcode_no=?");
			st.setInt(1,selectedBook.getBarcode_no());
			rs=st.executeQuery();
			if(rs.next()){
				int member_id=rs.getInt("member_id");
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				
				st.close();
				st=con.prepareStatement("update waiting_list set date_of_reservation=? where member_id = '"+member_id+"' and barcode_no = '"+selectedBook.getBarcode_no()+"'");
				st.setString(1, dateFormat.format(date));
				st.executeUpdate();
				st.close();
				
			}
			else{
				
				st.close();
			}
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
	
	public static int numberOfCurrentlyLoaned(){
		Connection con=null;
		PreparedStatement st=null;
	    ResultSet rs=null;
	    int count=0;
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
				
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			
			st=con.prepareStatement("select count(*) from view_books where member_id = '"+currentUser.getId()+"'");
		    rs=st.executeQuery();	
		    rs.next();
		    count=rs.getInt(1);	
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
		return count;
	}
	
	public static ArrayList<String> notifyWaiting(){
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs = null;
		String url="jdbc:mysql://localhost:3306/tutorial";
		String username="root";
		String password="umut";
	   	ArrayList<String> bookList = new ArrayList<String>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			
			st=con.prepareStatement("select name,author_name from waiting_list wl,book b where wl.barcode_no=b.barcode_no and member_id = '"+currentUser.getId()+"' and date_of_reservation is not null");
		    rs=st.executeQuery();	
			while(rs.next()){
				bookList.add(rs.getString("author_name") + " - " + rs.getString("name"));
			}
			st.close();
		}
		catch(Exception e){
			try {
				//e.printStackTrace();
				con.close();
				
			} catch (SQLException e1) {
				//e1.printStackTrace();
			}
		}
		return bookList;
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
			st=con.prepareStatement("delete from waiting_list where member_id = '"+currentUser.getId()+"' and barcode_no = '"+selectedBook.getBarcode_no()+"'");
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
}
