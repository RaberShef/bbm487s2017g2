package views;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controllers.BookController;
import models.BookModel;

public class ManipulateBooks {

	private JFrame frame;
	private JTextField textField;
	private JList<BookModel> list = new JList<BookModel>();
    private BookModel selectedBook=null;
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManipulateBooks window = new ManipulateBooks();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 */
	/**
	 * Create the application.
	 */
	public ManipulateBooks() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//frame.setVisible(true);
		frame = new JFrame("Manipulate Books");
		frame.setBounds(100, 100, 608, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(271, 45, 173, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSearchFor = new JLabel("Search for the book with");
		lblSearchFor.setBounds(10, 48, 150, 14);
		frame.getContentPane().add(lblSearchFor);
		
		String[] searchFor = { "Author", "Name", "Barcode"};
		JComboBox comboBox = new JComboBox(searchFor);
		comboBox.setBounds(172, 45, 89, 20);
		frame.getContentPane().add(comboBox);
		
		JButton btnBackToLogin = new JButton("Back to main menu");
		btnBackToLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenuLibrarianView mainMenu=new MainMenuLibrarianView();
				JFrame mainMenuFrame=mainMenu.getFrame();
				mainMenuFrame.setVisible(true);
				frame.dispose();
				
			}
		});
		
		btnBackToLogin.setBounds(430, 11, 152, 23);
		frame.getContentPane().add(btnBackToLogin);
				
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {			   
				String condition = (String)comboBox.getSelectedItem();
				String value = textField.getText();
				BookController.getBookByCondition(condition,value, list);
			}
		});
		btnSearch.setBounds(454, 44, 127, 23);
		frame.getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 108, 518, 243);
		frame.getContentPane().add(scrollPane);	
		
	
	
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		BookController.getBookByCondition(" "," ", list);
		JButton btnNewButton = new JButton("Edit book");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			 if(selectedBook!=null){	
				EditBook editBook=new EditBook(selectedBook);
				JFrame bookFrame=editBook.getFrame();
				bookFrame.setVisible(true);
				frame.dispose();
			  }
			}
		});
		btnNewButton.setBounds(375, 389, 152, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCreateNewUser = new JButton("Create new book");
		btnCreateNewUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			     CreateBook createBook=new CreateBook();
			     JFrame bookFrame=createBook.getFrame();
			     bookFrame.setVisible(true);
			     frame.dispose();
				
			}
		});
		
		btnCreateNewUser.setBounds(83, 389, 133, 25);
		frame.getContentPane().add(btnCreateNewUser);
		
		JButton btnDeleteUser = new JButton("Delete book");
		btnDeleteUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedBook!=null){
					
					BookController.deleteBook(selectedBook);
					BookController.getBookByCondition(" "," ", list);
					selectedBook=null;
				}
				
			}
		});
		btnDeleteUser.setBounds(226, 389, 139, 25);
		frame.getContentPane().add(btnDeleteUser);
		
		JLabel lblNewLabel = new JLabel("Name, Author name, Barcode, Page number, Printing year");
		lblNewLabel.setBounds(41, 86, 472, 14);
		frame.getContentPane().add(lblNewLabel);
		
		
		list.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		        if (!event.getValueIsAdjusting()){
		        	JList<BookModel> source = (JList<BookModel>) event.getSource();
		            selectedBook = (BookModel)source.getSelectedValue();
		        }
		    }
		});
	}
    public JFrame getFrame(){
		
		return frame;
		
	}
}
