package views;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controllers.UserController;
import models.UserModel;

public class EditMember {

	private JFrame frmEditMember;
	private JTextField txtUsername;
	private JPasswordField pwdPasssword;
	private JPasswordField passwordField;
	private JTextField txtName;
	private JLabel lblName;
	private JTextField txtSurname;
	private JLabel lblSurname;
	private JButton btnCreateNewUser;
	private JButton btnBackToManipulate;
	private JTextField txtemail;
    private UserModel selectedUserModel=null;
    private JLabel lblNewLabel;
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditMember window = new EditMember();
					window.frmEditMember.setVisible(true);
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
	public EditMember(UserModel userModel) {
		this.selectedUserModel=userModel;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEditMember = new JFrame();
		frmEditMember.setTitle("Edit Member");
		frmEditMember.setBounds(100, 100, 500, 500);
		frmEditMember.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEditMember.getContentPane().setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setText(selectedUserModel.getUsername());
		txtUsername.setToolTipText("");
		txtUsername.setBounds(202, 170, 154, 20);
		frmEditMember.getContentPane().add(txtUsername);
		txtUsername.setColumns(1);
		
		pwdPasssword = new JPasswordField();
		pwdPasssword.setBounds(202, 195, 154, 20);
		frmEditMember.getContentPane().add(pwdPasssword);
		pwdPasssword.setText(selectedUserModel.getPassword());
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(119, 173, 75, 14);
		frmEditMember.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(119, 198, 75, 14);
		frmEditMember.getContentPane().add(lblPassword);
		
		JLabel lblPasswordAgain = new JLabel("Password again :");
		lblPasswordAgain.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPasswordAgain.setBounds(92, 223, 102, 14);
		frmEditMember.getContentPane().add(lblPasswordAgain);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(202, 220, 154, 20);
		frmEditMember.getContentPane().add(passwordField);
		passwordField.setText(selectedUserModel.getPassword());
		
		txtName = new JTextField();
		txtName.setText(selectedUserModel.getName());
		txtName.setToolTipText("");
		txtName.setColumns(1);
		txtName.setBounds(202, 245, 154, 20);
		frmEditMember.getContentPane().add(txtName);
		
		lblName = new JLabel("Name :");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(140, 248, 55, 14);
		frmEditMember.getContentPane().add(lblName);
		
		txtSurname = new JTextField();
		txtSurname.setText(selectedUserModel.getSurname());
		txtSurname.setToolTipText("");
		txtSurname.setColumns(1);
		txtSurname.setBounds(202, 270, 154, 20);
		frmEditMember.getContentPane().add(txtSurname);
		
		lblSurname = new JLabel("Surname :");
		lblSurname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSurname.setBounds(130, 273, 64, 14);
		frmEditMember.getContentPane().add(lblSurname);
		
		JLabel lblEmail = new JLabel("E-Mail :");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(130, 298, 64, 14);
		frmEditMember.getContentPane().add(lblEmail);
		
		txtemail = new JTextField();
		txtemail.setToolTipText("");
		txtemail.setText(selectedUserModel.getEmail());
		txtemail.setColumns(1);
		txtemail.setBounds(202, 295, 154, 20);
		frmEditMember.getContentPane().add(txtemail);
		
		
		btnCreateNewUser = new JButton("Update member");
		btnCreateNewUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if((txtemail.getText()!=null&& txtemail.getText().trim().length()>0)&&(txtName.getText()!=null&&txtName.getText().trim().length()>0)
						&&(txtSurname.getText()!=null && txtSurname.getText().trim().length()>0)&&(txtUsername.getText()!=null && txtUsername.getText().trim().length()>0)
						&&(passwordField.getText()!=null&&passwordField.getText().trim().length()>0)
						&&(pwdPasssword.getText()!=null&& pwdPasssword.getText().trim().length()>0)
						&&(pwdPasssword.getText().equals(passwordField.getText()))){
						
						//UserModel user=new UserModel(0, txtUsername.getText(), passwordField.getText(),txtName.getText(),txtSurname.getText(),txtemail.getText(),false);
						selectedUserModel.setUsername(txtUsername.getText());
						selectedUserModel.setPassword(passwordField.getText());
						selectedUserModel.setName(txtName.getText());
						selectedUserModel.setSurname(txtSurname.getText());
						selectedUserModel.setEmail(txtemail.getText());
					     UserController.updateUser(selectedUserModel);
						ManipulateMembers manipulateMembers=new ManipulateMembers();
						JFrame memberFrame=manipulateMembers.getFrame();
						memberFrame.setVisible(true);
						frmEditMember.dispose();        		
				  	}
			}
		});
		btnCreateNewUser.setBounds(194, 326, 162, 37);
		frmEditMember.getContentPane().add(btnCreateNewUser);
		
		btnBackToManipulate = new JButton("Back to manipulate members");
		btnBackToManipulate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ManipulateMembers manipulateMembers=new ManipulateMembers();
				JFrame memberFrame=manipulateMembers.getFrame();
				memberFrame.setVisible(true);
				frmEditMember.dispose();  
				
			}
		});
		btnBackToManipulate.setBounds(264, 11, 210, 23);
		frmEditMember.getContentPane().add(btnBackToManipulate);
		
		
		
		BufferedImage myPicture=null;
		try {
			myPicture = ImageIO.read(new File("resources/update.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		lblNewLabel = new JLabel(new ImageIcon(myPicture));
		
		lblNewLabel.setBounds(107, 45, 284, 114);
		frmEditMember.getContentPane().add(lblNewLabel);
		
	
	}
	public JFrame getFrame() {
		return frmEditMember;
	}
}
