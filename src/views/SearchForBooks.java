package views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import controllers.BookController;
import models.BookModel;
import javax.swing.JPanel;

public class SearchForBooks extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField textField;
    private JList<BookModel> list = new JList<BookModel>();
	public SearchForBooks() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame("Search for books without login");
		frame.setBounds(100, 100, 646, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(290, 45, 212, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSearchFor = new JLabel("Search for the book with");
		lblSearchFor.setBounds(41, 48, 150, 14);
		frame.getContentPane().add(lblSearchFor);
		
		String[] searchFor = { "Author", "Name", "Barcode" };
		JComboBox comboBox = new JComboBox(searchFor);
		comboBox.setBounds(189, 45, 89, 20);
		frame.getContentPane().add(comboBox);
		
		JButton btnBackToLogin = new JButton("Back to login");
		btnBackToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginView login = new LoginView();
				JFrame loginFr = login.getFrame();
				loginFr.setVisible(true);
				frame.dispose();
			}
		});
		btnBackToLogin.setBounds(478, 11, 152, 23);
		frame.getContentPane().add(btnBackToLogin);
			
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String condition = (String)comboBox.getSelectedItem();
				String value = textField.getText();
				BookController.getBookByCondition(condition,value, list);
			}
		});
		btnSearch.setBounds(506, 44, 124, 23);
		frame.getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 111, 392, 303);
		frame.getContentPane().add(scrollPane);
		
		/*JLabel bookName = new JLabel("");
		bookName.setBounds(301, 132, 162, 14);
		frame.getContentPane().add(bookName);*/
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		BookController.getBookByCondition(" "," ", list);
		BufferedImage myPicture=null;
		try {
			myPicture = ImageIO.read(new File("resources/search.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel panel = new JLabel(new ImageIcon(myPicture));
		panel.setBounds(410, 144, 220, 250);
		frame.getContentPane().add(panel);
		
		JLabel lblName = new JLabel("Name, Author name, Barcode, Page number, Printing year");
		lblName.setBounds(10, 86, 392, 14);
		frame.getContentPane().add(lblName);
		frame.setResizable(false);
		
	}

	public JFrame getFrame() {
		return frame;
	}
}
