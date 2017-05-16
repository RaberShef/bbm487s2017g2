package views;
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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controllers.UserController;
import models.BookModel;
import javax.swing.JPanel;

public class ViewMyBooks extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField textField;
	private JList<BookModel> list = new JList<BookModel>();
	private BookModel selectedBook;

	public ViewMyBooks() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("View My Books");
		frame.setBounds(100, 100, 639, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(290, 45, 200, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSearchFor = new JLabel("Search for the book with");
		lblSearchFor.setBounds(41, 48, 150, 14);
		frame.getContentPane().add(lblSearchFor);
		
		String[] searchFor = { "Author", "Name", "Barcode" };
		JComboBox comboBox = new JComboBox(searchFor);
		comboBox.setBounds(189, 45, 89, 20);
		frame.getContentPane().add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 120, 371, 294);
		frame.getContentPane().add(scrollPane);	
				
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		UserController.getLoanedBooksByCondition(" "," ", list);	
		
		JButton btnBackToMain= new JButton("Back to main menu");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuMemberView memberMain = new MainMenuMemberView();
				JFrame memberFr = memberMain.getFrame();
				memberFr.setVisible(true);
				frame.dispose();
			}
		});
		btnBackToMain.setBounds(469, 11, 152, 23);
		frame.getContentPane().add(btnBackToMain);
				
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(500, 44, 121, 23);
		frame.getContentPane().add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserController.getLoanedBooksByCondition((String)comboBox.getSelectedItem(),textField.getText(), list);	
			}
		});
		
		JButton btnSelfReturn = new JButton("Return book");
		btnSelfReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedBook==null){
					return;
				}
				if(UserController.checkIfFined(selectedBook)){
					JOptionPane.showMessageDialog(frame, "You have to pay the fine for this book before returning it.");
					return;
				}
				UserController.returnBook(selectedBook);
				JOptionPane.showMessageDialog(frame, "Book returned successfully.");
				//DefaultListModel listModel=new DefaultListModel();
				//list.setModel(listModel);
				UserController.getLoanedBooksByCondition(" "," ", list);
			}
		});
		btnSelfReturn.setBounds(436, 343, 173, 43);
		frame.getContentPane().add(btnSelfReturn);
		
		BufferedImage myPicture=null;
		try {
			myPicture = ImageIO.read(new File("resources/loan.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel panel = new JLabel(new ImageIcon(myPicture));
		panel.setBounds(422, 134, 201, 189);
		frame.getContentPane().add(panel);
		
		JLabel lblName = new JLabel("Name, Author name, Barcode, Page number, Printing year");
		lblName.setBounds(42, 95, 370, 14);
		frame.getContentPane().add(lblName);
		
		list.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		        if (!event.getValueIsAdjusting()){
		        	@SuppressWarnings("unchecked")
					JList<BookModel> source = (JList<BookModel>) event.getSource();
		            selectedBook = (BookModel)source.getSelectedValue();
		        }
		    }
		});
		frame.setResizable(false);
	}
	
	public JFrame getFrame(){
		return frame;
	}
}
