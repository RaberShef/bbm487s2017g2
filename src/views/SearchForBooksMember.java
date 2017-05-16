package views;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controllers.BookController;
import controllers.UserController;
import models.BookModel;
import models.UserModel;

import javax.swing.JPanel;

public class SearchForBooksMember extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField textField;
	private JList<BookModel> list = new JList<BookModel>();
    private BookModel selectedBook;

	public SearchForBooksMember() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Search for books");
		frame.setBounds(100, 100, 631, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(301, 45, 183, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSearchFor = new JLabel("Search for the book with");
		lblSearchFor.setBounds(41, 48, 150, 14);
		frame.getContentPane().add(lblSearchFor);
		
		String[] searchFor = { "Author", "Name", "Barcode" };
		JComboBox comboBox = new JComboBox(searchFor);
		comboBox.setBounds(189, 45, 89, 20);
		frame.getContentPane().add(comboBox);
		
		JButton btnBackToLogin = new JButton("Back to main menu");
		btnBackToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuMemberView memberMain = new MainMenuMemberView();
				JFrame memberFr = memberMain.getFrame();
				memberFr.setVisible(true);
				frame.dispose();
			}
		});
		btnBackToLogin.setBounds(465, 11, 150, 23);
		frame.getContentPane().add(btnBackToLogin);
				
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String condition = (String)comboBox.getSelectedItem();
				String value = textField.getText();
				BookController.getBookByCondition(condition,value, list);
			}
		});
		
		btnSearch.setBounds(488, 44, 127, 23);
		frame.getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 102, 386, 312);
		frame.getContentPane().add(scrollPane);	
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		BookController.getBookByCondition(" "," ", list);
		
		JButton btnSelfcheckout = new JButton("Proceed to checkout");
		btnSelfcheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedBook == null){
					return;
				}
				if(UserController.numberOfCurrentlyLoaned() >= 3){
					JOptionPane.showMessageDialog(frame, "You can loan at most three books at a time");
					return;
				}
				SelfCheckout selfCheckout = new SelfCheckout(selectedBook);
				JFrame selfCheckoutFr = selfCheckout.getFrame();
				selfCheckoutFr.setVisible(true);
				frame.dispose();
			}
		});
		btnSelfcheckout.setBounds(442, 378, 173, 36);
		frame.getContentPane().add(btnSelfcheckout);
		
		JButton btnAddNameTo = new JButton("Add name to waiting list");
		btnAddNameTo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
	          if(selectedBook==null){
	        	  return;
	          }
	          
	          boolean control = UserController.addToWaitingList(selectedBook.getBarcode_no());
	          if(control){
	        	  JOptionPane.showMessageDialog(frame, "The book named " + selectedBook.getName() + " has been successfully added to the waiting list");
	          }
	          else {
	        	  JOptionPane.showMessageDialog(frame, "The book named " + selectedBook.getName() + " cannot be added to the waiting list");
	          }
			}
		});
		btnAddNameTo.setBounds(442, 331, 173, 36);
		frame.getContentPane().add(btnAddNameTo);
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setBackground(UIManager.getColor("Button.background"));
		textArea.setLineWrap(true);
		textArea.setBounds(437, 191, 173, 107);
		frame.getContentPane().add(textArea);
		
		JLabel lblName = new JLabel("Name, Author name, Barcode, Page number, Printing year");
		lblName.setBounds(41, 77, 386, 14);
		frame.getContentPane().add(lblName);
		
		BufferedImage myPicture=null;
		try {
			myPicture = ImageIO.read(new File("resources/search.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		list.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		        if (!event.getValueIsAdjusting()){
					@SuppressWarnings("unchecked")
					JList<BookModel> source = (JList<BookModel>) event.getSource();
		            selectedBook = (BookModel)source.getSelectedValue();
		            boolean isInStock = false;
		            if(selectedBook == null){
		            	return;
		            }
		            isInStock = BookController.checkStock(selectedBook);
		            boolean isWaited = UserController.checkIfWaited(selectedBook.getBarcode_no());
		            boolean isTheOneWaiting = UserController.checkIfFirstInWList(selectedBook.getBarcode_no());
		            boolean isFined = UserController.checkIfFined();
		            if(isFined){
		            	textArea.setText("You haven't paid your fine yet. You can't loan or queue for a book until you have paid it.");
		            	btnAddNameTo.setEnabled(false);
		            	btnSelfcheckout.setEnabled(false);
		            }
		            else if(isInStock && !isWaited){
		            	textArea.setText("Chosen book is available, you may proceed to checkout.");
		            	btnAddNameTo.setEnabled(false);
		            	btnSelfcheckout.setEnabled(true);
		            }
		            else if(isInStock && isWaited){
		            	if(isTheOneWaiting){
		            		textArea.setText("Chosen book is in stock and you are first in the waiting list. You may proceed to checkout.");
			            	btnAddNameTo.setEnabled(false);
			            	btnSelfcheckout.setEnabled(true);
		            	}
		            	else{
		            		textArea.setText("Chosen book is in stock but there are people in waiting list to loan it, you may add your name to waiting list.");
			            	btnAddNameTo.setEnabled(true);
			            	btnSelfcheckout.setEnabled(false);
		            	}
		            }
		            else if(!isInStock){
		            	textArea.setText("Chosen book is not in stock, you may add your name to waiting list.");
		            	btnAddNameTo.setEnabled(true);
		            	btnSelfcheckout.setEnabled(false);
		            }
		            
		        }
		    }
		});
		
		frame.setResizable(false);
	}

	public JFrame getFrame() {
		return frame;
	}
}
