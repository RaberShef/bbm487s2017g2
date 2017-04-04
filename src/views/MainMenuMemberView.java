package views;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuMemberView extends Frame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public MainMenuMemberView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Member Main Menu");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(385, 11, 89, 23);
		frame.getContentPane().add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginView lgn = new LoginView();
				JFrame lgnFr = lgn.getFrame();
				lgnFr.setVisible(true);
				frame.dispose();
			}
		});
		
		JButton btnSearchForBooks = new JButton("Search for Books");
		btnSearchForBooks.setBounds(161, 122, 169, 71);
		btnSearchForBooks.setEnabled(false);
		frame.getContentPane().add(btnSearchForBooks);
		
		JButton btnViewMyBooks = new JButton("View My Books");
		btnViewMyBooks.setBounds(161, 204, 169, 71);
		btnViewMyBooks.setEnabled(false);
		frame.getContentPane().add(btnViewMyBooks);
		
		JButton btnPayFine = new JButton("Pay Fine");
		btnPayFine.setBounds(161, 286, 169, 71);
		btnPayFine.setEnabled(false);
		frame.getContentPane().add(btnPayFine);
		
		JLabel lblWelcomeMember = new JLabel("Welcome, Member X");
		lblWelcomeMember.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblWelcomeMember.setBounds(10, 15, 169, 30);
		frame.getContentPane().add(lblWelcomeMember);
	}

	public JFrame getFrame(){
		return frame;
	}
}
