package models;

public class UserModel {
	private String username;
	private String password;
	private String name;
	private String surname;
    private String email;
    private boolean isLibrarian;

    public UserModel(String username, String password, String name, String surname, String email, boolean isLibrarian) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.isLibrarian = isLibrarian;
    }
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isLibrarian() {
		return isLibrarian;
	}

	public void setLibrarian(boolean isLibrarian) {
		this.isLibrarian = isLibrarian;
	}
}
