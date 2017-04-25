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

public class MainMenuMemberView extends Frame{

	private static final long serialVersionUID = 1L;
	private JFrame frame;

	public MainMenuMemberView() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Member Main Menu");
		frame.setBounds(100, 100, 578, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(404, 30, 106, 30);
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
		btnSearchForBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchForBooksMember searchBooksMember = new SearchForBooksMember();
				JFrame searchBooksMemberFr = searchBooksMember.getFrame();
				searchBooksMemberFr.setVisible(true);
				frame.dispose();
			}
		});
		btnSearchForBooks.setBounds(46, 125, 158, 30);
		btnSearchForBooks.setEnabled(true);
		frame.getContentPane().add(btnSearchForBooks);
		
		JButton btnViewMyBooks = new JButton("View My Books");
		btnViewMyBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewMyBooks viewMyBooks = new ViewMyBooks();
				JFrame viewMyBooksFr = viewMyBooks.getFrame();
				viewMyBooksFr.setVisible(true);
				frame.dispose();
			}
		});
		btnViewMyBooks.setBounds(232, 125, 141, 30);
		btnViewMyBooks.setEnabled(true);
		frame.getContentPane().add(btnViewMyBooks);
		
		JButton btnPayFine = new JButton("Pay Fine");
		btnPayFine.setBounds(394, 125, 141, 30);
		btnPayFine.setEnabled(false);
		frame.getContentPane().add(btnPayFine);
		
		JLabel lblWelcomeMember = new JLabel("Welcome, Member "+ UserController.getUserModel().getName());
		
		lblWelcomeMember.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblWelcomeMember.setBounds(42, 71, 364, 30);
		frame.getContentPane().add(lblWelcomeMember);
		
		
		BufferedImage myPicture=null;
		try {
			myPicture = ImageIO.read(new File("resources/beytepe.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JLabel Paintpanel = new JLabel(new ImageIcon(myPicture));

		Paintpanel.setBounds(59, 197, 462, 208);
		frame.getContentPane().add(Paintpanel);
		frame.setResizable(false);
	}

	public JFrame getFrame(){
		return frame;
	}
}
