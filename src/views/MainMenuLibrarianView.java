package views;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import controllers.UserController;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

public class MainMenuLibrarianView extends Frame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;

	public MainMenuLibrarianView() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Librarian Main Menu");
		frame.setBounds(100, 100, 643, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(476, 25, 122, 30);
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
		btnSearchForBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearchForBooks.setBounds(91, 386, 208, 50);
		btnSearchForBooks.setEnabled(false);
		frame.getContentPane().add(btnSearchForBooks);
		
		JButton btnViewMyBooks = new JButton("Manipulate Members");
		btnViewMyBooks.setBounds(350, 386, 210, 50);
		btnViewMyBooks.setEnabled(false);
		frame.getContentPane().add(btnViewMyBooks);
		
		JLabel lblWelcomeMember = new JLabel("Welcome, Librarian "+ UserController.getUserModel().getName());
		lblWelcomeMember.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblWelcomeMember.setBounds(66, 48, 371, 30);
		frame.getContentPane().add(lblWelcomeMember);
		
		
		BufferedImage myPicture=null;
		try {
			myPicture = ImageIO.read(new File("resources/admin.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel panel = new JLabel(new ImageIcon(myPicture));
		panel.setBounds(81, 89, 479, 286);
		frame.getContentPane().add(panel);
		frame.setResizable(false);
		}

	public JFrame getFrame(){
		return frame;
	}
}
