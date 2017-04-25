package views;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EditMember extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField pwdPasssword;
	private JPasswordField pwdPassword2;
	private JTextField txtName;
	private JLabel lblName;
	private JTextField txtSurname;
	private JLabel lblSurname;
	private JButton btnUpdateMember;
	private JButton btnBackToManipulate;
	private JTextField txtEmail;

	/**
	 * Create the application.
	 */
	public EditMember() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Edit Member");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setText("rahmi.sefkatli");
		txtUsername.setToolTipText("");
		txtUsername.setBounds(202, 114, 154, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(1);
		
		pwdPasssword = new JPasswordField();
		pwdPasssword.setBounds(202, 139, 154, 20);
		frame.getContentPane().add(pwdPasssword);
		pwdPasssword.setText("Deneme");
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(119, 117, 75, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(119, 142, 75, 14);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblPasswordAgain = new JLabel("Password again :");
		lblPasswordAgain.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPasswordAgain.setBounds(92, 167, 102, 14);
		frame.getContentPane().add(lblPasswordAgain);
		
		pwdPassword2 = new JPasswordField();
		pwdPassword2.setBounds(202, 164, 154, 20);
		frame.getContentPane().add(pwdPassword2);
		pwdPassword2.setText("Deneme");
		
		txtName = new JTextField();
		txtName.setText("Rahmi Berk");
		txtName.setToolTipText("");
		txtName.setColumns(1);
		txtName.setBounds(202, 188, 154, 20);
		frame.getContentPane().add(txtName);
		
		lblName = new JLabel("Name :");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(139, 191, 55, 14);
		frame.getContentPane().add(lblName);
		
		txtSurname = new JTextField();
		txtSurname.setText("\u015Eefkatli");
		txtSurname.setToolTipText("");
		txtSurname.setColumns(1);
		txtSurname.setBounds(202, 211, 154, 20);
		frame.getContentPane().add(txtSurname);
		
		lblSurname = new JLabel("Surname :");
		lblSurname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSurname.setBounds(130, 214, 64, 14);
		frame.getContentPane().add(lblSurname);
		
		btnUpdateMember = new JButton("Update member");
		btnUpdateMember.setBounds(92, 323, 301, 53);
		frame.getContentPane().add(btnUpdateMember);
		
		btnBackToManipulate = new JButton("Back to manipulate members");
		btnBackToManipulate.setBounds(264, 11, 210, 23);
		frame.getContentPane().add(btnBackToManipulate);
		
		JLabel lblEmail = new JLabel("E-Mail :");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(130, 236, 64, 14);
		frame.getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setToolTipText("");
		txtEmail.setText("berksefkatli@gmail.com");
		txtEmail.setColumns(1);
		txtEmail.setBounds(202, 233, 154, 20);
		frame.getContentPane().add(txtEmail);
	}
	
	public JFrame getFrame(){
		return frame;
	}
}
