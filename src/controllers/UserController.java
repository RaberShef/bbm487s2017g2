package controllers;

import java.util.ArrayList;
import models.UserModel;

public class UserController {
	private static ArrayList<UserModel> userList = new ArrayList<UserModel>();
	
	public static void initializeUsers() {
		UserModel member = new UserModel("member", "1234", "Member", "Member surname", "member@gmail.com", false);
		UserModel librarian = new UserModel("librarian", "1234", "Librarian", "Librarian surname", "librarian@gmail.com", true);
		userList.add(member);
		userList.add(librarian);
	}
	
	public static int login(String inputUsername, String inputPassword){
		for (int i = 0; i < userList.size(); i++) {
			if(inputUsername.equals(userList.get(i).getUsername())){
				if(inputPassword.equals(userList.get(i).getPassword())){
					if(userList.get(i).isLibrarian())
						return 1;
					else
						return 0;
				}
			}
		}
		return -1;
	}
}
