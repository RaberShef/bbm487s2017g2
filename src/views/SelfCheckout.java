package views;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import controllers.UserController;
import models.BookModel;

public class SelfCheckout extends Frame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private BookModel selectedBook;

	public SelfCheckout(BookModel selectedBook) {
		this.selectedBook = selectedBook;
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Self-Checkout");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea txtrTheBookWith = new JTextArea();
		txtrTheBookWith.setEditable(false);
		txtrTheBookWith.setLineWrap(true);
		txtrTheBookWith.setText("The book you have chosen will be loaned to you for 30 days.  \r\nYou must return it in 30 days or you will be fined 5 TL for each extra day. Do you agree?");
		txtrTheBookWith.setWrapStyleWord(true);
		txtrTheBookWith.setBackground(UIManager.getColor("Button.background"));
		txtrTheBookWith.setBounds(66, 127, 341, 91);
		frame.getContentPane().add(txtrTheBookWith);
		
		JButton btnYesIAgree = new JButton("Yes, I agree");
		btnYesIAgree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int valid = UserController.checkoutBook(selectedBook);
				if(valid == -1){
					JOptionPane.showMessageDialog(frame, "Book cannot be loaned.");
				}
				else{
					JOptionPane.showMessageDialog(frame, "Book loaned successfully.");
				}
				SearchForBooksMember searchBooksMember = new SearchForBooksMember();
				JFrame searchBooksMemberFr = searchBooksMember.getFrame();
				searchBooksMemberFr.setVisible(true);
				frame.dispose();
			}
		});
		btnYesIAgree.setBounds(53, 250, 166, 60);
		frame.getContentPane().add(btnYesIAgree);
		
		JButton btnNoIDont = new JButton("No, I don't agree");
		btnNoIDont.setBounds(262, 250, 166, 60);
		btnNoIDont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchForBooksMember searchBooksMember = new SearchForBooksMember();
				JFrame searchBooksMemberFr = searchBooksMember.getFrame();
				searchBooksMemberFr.setVisible(true);
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnNoIDont);
		frame.setResizable(false);
	}
	public JFrame getFrame(){
		return frame;
	}
}
