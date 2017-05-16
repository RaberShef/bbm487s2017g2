package views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import controllers.BookController;
import controllers.UserController;
import models.BookModel;
import java.awt.Font;

public class PayFineView {

	private JFrame frame;
	private JList<BookModel> list = new JList<BookModel>();
	private JList<Integer> list2 = new JList<Integer>();
	/**
	 * Create the application.
	 */
	public PayFineView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//frame.setVisible(true);
		frame = new JFrame("Pay Fine");
		frame.setBounds(100, 100, 608, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
						
		JButton btnBackToLogin = new JButton("Back to main menu");
		btnBackToLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenuMemberView mainMenu = new MainMenuMemberView();
				JFrame mainMenuFrame=mainMenu.getFrame();
				mainMenuFrame.setVisible(true);
				frame.dispose();
			}
		});
		
		btnBackToLogin.setBounds(430, 11, 152, 23);
		frame.getContentPane().add(btnBackToLogin);
						
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 80, 378, 271);
		frame.getContentPane().add(scrollPane);	
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JLabel lblNewLabel = new JLabel("Name, Author name, Barcode, Page number, Printing Year");
		lblNewLabel.setBounds(41, 55, 346, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(423, 80, 125, 271);
		frame.getContentPane().add(scrollPane_1);
		
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(list2);
		
		int totalFine = BookController.getFinedBooks(list, list2);
		
		JLabel lblFinedAmount = new JLabel("Fined Amount");
		lblFinedAmount.setBounds(450, 55, 77, 14);
		frame.getContentPane().add(lblFinedAmount);
		
		JLabel lbltotalFine = new JLabel("Total Fine : " + String.valueOf(totalFine));
		lbltotalFine.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbltotalFine.setBounds(228, 362, 260, 23);
		frame.getContentPane().add(lbltotalFine);
		
		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(UserController.payFine()){
					JOptionPane.showMessageDialog(frame, "All fines have been paid.");
					int totalFine = BookController.getFinedBooks(list, list2);
					lbltotalFine.setText("Total Fine : " + totalFine);
				}
				else{
					JOptionPane.showMessageDialog(frame, "Error while trying to pay fines.");
				}
			}
		});
		btnPay.setBounds(256, 407, 110, 37);
		frame.getContentPane().add(btnPay);
		frame.setResizable(false);
	}
    public JFrame getFrame(){
		return frame;
	}
}
