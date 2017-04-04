package views;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuLibrarianView extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public MainMenuLibrarianView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Librarian Main Menu");
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
		
		JButton btnSearchForBooks = new JButton("Manipulate Books");
		btnSearchForBooks.setBounds(170, 112, 160, 120);
		btnSearchForBooks.setEnabled(false);
		frame.getContentPane().add(btnSearchForBooks);
		
		JButton btnViewMyBooks = new JButton("Manipulate Members");
		btnViewMyBooks.setBounds(170, 247, 160, 120);
		btnViewMyBooks.setEnabled(false);
		frame.getContentPane().add(btnViewMyBooks);
		
		JLabel lblWelcomeMember = new JLabel("Welcome, Librarian X");
		lblWelcomeMember.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblWelcomeMember.setBounds(10, 15, 169, 30);
		frame.getContentPane().add(lblWelcomeMember);
	}

	public JFrame getFrame(){
		return frame;
	}
}
