package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import controllers.UserController;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginView extends Frame{

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField pwdPasssword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView window = new LoginView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginView() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Library Book Loan System");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setToolTipText("");
		txtUsername.setBounds(219, 253, 173, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(1);
		
		pwdPasssword = new JPasswordField();
		pwdPasssword.setBounds(219, 284, 173, 20);
		frame.getContentPane().add(pwdPasssword);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setBounds(117, 256, 100, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(117, 287, 100, 14);
		frame.getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				switch(UserController.login(txtUsername.getText(), pwdPasssword.getText())) {
					case -1: 
						JOptionPane.showMessageDialog(frame, "Username and password does not match");
						break;
					case 0:
						MainMenuMemberView memberMain = new MainMenuMemberView();
						JFrame memberFr = memberMain.getFrame();
						memberFr.setVisible(true);
						frame.dispose();
						break;
					case 1:
						MainMenuLibrarianView librarianMain = new MainMenuLibrarianView();
						JFrame librarianFr = librarianMain.getFrame();
						librarianFr.setVisible(true);
						frame.dispose();
						break;
				}			
			}
		});
		btnLogin.setBounds(108, 328, 120, 23);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblLibraryBookLoan = new JLabel("Library Book Loan System");
		lblLibraryBookLoan.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblLibraryBookLoan.setBounds(10, 11, 464, 79);
		frame.getContentPane().add(lblLibraryBookLoan);
		
		JButton btnSearchForBooks = new JButton("Search for books");
		btnSearchForBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchForBooks searchBooks = new SearchForBooks();
				JFrame searchBooksFr = searchBooks.getFrame();
				searchBooksFr.setVisible(true);
				frame.dispose();
			}
		});
		btnSearchForBooks.setEnabled(true);
		btnSearchForBooks.setBounds(238, 328, 154, 23);
		frame.getContentPane().add(btnSearchForBooks);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("resources/library.jpg"));
		lblNewLabel.setBounds(117, 88, 261, 142);
		frame.getContentPane().add(lblNewLabel);
		frame.setResizable(false);
	}
	
	public JFrame getFrame(){
		return frame;
	}
}
