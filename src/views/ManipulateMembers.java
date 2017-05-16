package views;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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

import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractListModel;
import javax.swing.SwingConstants;

public class ManipulateMembers {

	private JFrame frame;
	private JTextField textField;
	private JList<UserModel> list = new JList<UserModel>();
	private UserModel selectedUserModel=null;
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManipulateMembers window = new ManipulateMembers();
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
	public ManipulateMembers() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Manipulate Members");
		frame.setBounds(100, 100, 583, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(282, 45, 145, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSearchFor = new JLabel("Search for the member with");
		lblSearchFor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSearchFor.setBounds(6, 48, 173, 14);
		frame.getContentPane().add(lblSearchFor);
		
		//String[] searchFor = { "Author", "Name", "Barcode" };
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Name", "Surname", "Username", "E-Mail"}));
		comboBox.setBounds(189, 45, 89, 20);
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
		btnBackToLogin.setBounds(397, 11, 160, 23);
		frame.getContentPane().add(btnBackToLogin);
		
		String[] foundBooks = { "Silmarillion, J.R.R. Tolkien, 1276312", "Hobbit, J.R.R. Tolkien, 1214312" };
		
		
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 124, 334, 290);
		frame.getContentPane().add(scrollPane);	
		
	
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		UserController.getMemberByCondition(" "," ", list);
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String condition = (String)comboBox.getSelectedItem();
				String value = textField.getText();
				UserController.getMemberByCondition(condition,value, list);
			}
		});
		btnSearch.setBounds(431, 44, 126, 23);
		frame.getContentPane().add(btnSearch);
		
		
		
		
		JButton btnNewButton = new JButton("Edit member");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(selectedUserModel!=null){
				     EditMember editMember=new EditMember(selectedUserModel);
				     JFrame editMemberFrame=editMember.getFrame();
				     editMemberFrame.setVisible(true);
				     frame.dispose();
				}
			}
		});
		btnNewButton.setBounds(416, 144, 133, 43);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCreateNewmember = new JButton("Create member");
		btnCreateNewmember.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				     CreateMember createBook=new CreateMember();
				     JFrame memberFrame=createBook.getFrame();
				     memberFrame.setVisible(true);
				     frame.dispose();
				
			}
		});
		btnCreateNewmember.setBounds(416, 256, 133, 51);
		frame.getContentPane().add(btnCreateNewmember);
		
		JButton btnDeletemember = new JButton("Delete member");
		btnDeletemember.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			   if(selectedUserModel!=null){
				   UserController.deleteUser(selectedUserModel);
				   UserController.getMemberByCondition(" "," ", list);
			   }
				
			}
		});
		btnDeletemember.setBounds(416, 202, 133, 43);
		frame.getContentPane().add(btnDeletemember);
		
		JLabel lblName = new JLabel("Name, Surname, Email");
		lblName.setBounds(41, 99, 334, 14);
		frame.getContentPane().add(lblName);
		
		
		list.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		        if (!event.getValueIsAdjusting()){
		        	JList<UserModel> source = (JList<UserModel>) event.getSource();
		            selectedUserModel = (UserModel)source.getSelectedValue();
		            
		        }
		    }
		});
		
	}
	
	public JFrame getFrame(){
		
		return frame;
		
	}
}
