package views;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controllers.UserController;
import models.UserModel;

public class CreateMember {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField pwdPasssword;
	private JPasswordField passwordField;
	private JTextField txtName;
	private JLabel lblName;
	private JTextField txtSurname;
	private JLabel lblSurname;
	private JTextField txtEmail;
	private JLabel lblEmail;
	private JButton btnCreateNewUser;
	private JButton btnBackToManipulate;
	private JLabel label;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateMember window = new CreateMember();
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
	public CreateMember() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Create Member");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setToolTipText("");
		txtUsername.setBounds(222, 211, 153, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(1);
		
		pwdPasssword = new JPasswordField();
		pwdPasssword.setBounds(222, 237, 153, 20);
		frame.getContentPane().add(pwdPasssword);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(137, 214, 75, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(139, 240, 73, 14);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblPasswordAgain = new JLabel("Password again :");
		lblPasswordAgain.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPasswordAgain.setBounds(111, 264, 102, 14);
		frame.getContentPane().add(lblPasswordAgain);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(222, 261, 153, 20);
		frame.getContentPane().add(passwordField);
		
		txtName = new JTextField();
		txtName.setToolTipText("");
		txtName.setColumns(1);
		txtName.setBounds(222, 286, 153, 20);
		frame.getContentPane().add(txtName);
		
		lblName = new JLabel("Name :");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(157, 289, 55, 14);
		frame.getContentPane().add(lblName);
		
		txtSurname = new JTextField();
		txtSurname.setToolTipText("");
		txtSurname.setColumns(1);
		txtSurname.setBounds(222, 311, 153, 20);
		frame.getContentPane().add(txtSurname);
		
		lblSurname = new JLabel("Surname :");
		lblSurname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSurname.setBounds(148, 314, 64, 14);
		frame.getContentPane().add(lblSurname);
		
		txtEmail = new JTextField();
		txtEmail.setToolTipText("");
		txtEmail.setColumns(1);
		txtEmail.setBounds(222, 336, 153, 20);
		frame.getContentPane().add(txtEmail);
		
		lblEmail = new JLabel("E-Mail :");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(174, 339, 41, 14);
		frame.getContentPane().add(lblEmail);
		
		btnCreateNewUser = new JButton("Create new member");
		btnCreateNewUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			  
				if((txtEmail.getText()!=null&& txtEmail.getText().trim().length()>0)&&(txtName.getText()!=null&&txtName.getText().trim().length()>0)
					&&(txtSurname.getText()!=null && txtSurname.getText().trim().length()>0)&&(txtUsername.getText()!=null && txtUsername.getText().trim().length()>0)
					&&(passwordField.getText()!=null&&passwordField.getText().trim().length()>0)
					&&(pwdPasssword.getText()!=null&& pwdPasssword.getText().trim().length()>0)
					&&(pwdPasssword.getText().equals(passwordField.getText()))){
					
					UserModel user=new UserModel(0, txtUsername.getText(), passwordField.getText(),txtName.getText(),txtSurname.getText(),txtEmail.getText(),false);
					UserController.createNewUser(user);
					ManipulateMembers manipulateMembers=new ManipulateMembers();
					JFrame memberFrame=manipulateMembers.getFrame();
					memberFrame.setVisible(true);
					frame.dispose();        		
			  	}
				else{
					JOptionPane.showMessageDialog(frame, "New member cannot be created");
					
				}
				
			
			}
		});
		btnCreateNewUser.setBounds(222, 367, 153, 29);
		frame.getContentPane().add(btnCreateNewUser);
		
		btnBackToManipulate = new JButton("Back to manipulate members");
		btnBackToManipulate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ManipulateMembers manipulateMembers=new ManipulateMembers();
				JFrame memberFrame=manipulateMembers.getFrame();
				memberFrame.setVisible(true);
				frame.dispose();  
				
			}
		});
		btnBackToManipulate.setBounds(266, 11, 208, 23);
		frame.getContentPane().add(btnBackToManipulate);
		
		
		BufferedImage myPicture=null;
		try {
			myPicture = ImageIO.read(new File("resources/create.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		label = new JLabel(new ImageIcon(myPicture));
		label.setBounds(123, 65, 300, 118);
		frame.getContentPane().add(label);
	}
	public JFrame getFrame() {
		return frame;
	}
}
