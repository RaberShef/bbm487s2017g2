package views;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CreateMember extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField pwdPasssword;
	private JPasswordField pwdPassword2;
	private JTextField txtName;
	private JLabel lblName;
	private JTextField txtSurname;
	private JLabel lblSurname;
	private JTextField txtEmail;
	private JLabel lblEmail;
	private JButton btnCreateNewMember;
	private JButton btnBackToManipulate;

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
		txtUsername.setBounds(222, 113, 120, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(1);
		
		pwdPasssword = new JPasswordField();
		pwdPasssword.setBounds(222, 144, 120, 20);
		frame.getContentPane().add(pwdPasssword);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(137, 113, 75, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(139, 144, 73, 14);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblPasswordAgain = new JLabel("Password again :");
		lblPasswordAgain.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPasswordAgain.setBounds(110, 175, 102, 14);
		frame.getContentPane().add(lblPasswordAgain);
		
		pwdPassword2 = new JPasswordField();
		pwdPassword2.setBounds(222, 175, 120, 20);
		frame.getContentPane().add(pwdPassword2);
		
		txtName = new JTextField();
		txtName.setToolTipText("");
		txtName.setColumns(1);
		txtName.setBounds(222, 203, 120, 20);
		frame.getContentPane().add(txtName);
		
		lblName = new JLabel("Name :");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(157, 203, 55, 14);
		frame.getContentPane().add(lblName);
		
		txtSurname = new JTextField();
		txtSurname.setToolTipText("");
		txtSurname.setColumns(1);
		txtSurname.setBounds(222, 230, 120, 20);
		frame.getContentPane().add(txtSurname);
		
		lblSurname = new JLabel("Surname :");
		lblSurname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSurname.setBounds(148, 230, 64, 14);
		frame.getContentPane().add(lblSurname);
		
		txtEmail = new JTextField();
		txtEmail.setToolTipText("");
		txtEmail.setColumns(1);
		txtEmail.setBounds(222, 261, 120, 20);
		frame.getContentPane().add(txtEmail);
		
		lblEmail = new JLabel("E-Mail :");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(171, 261, 41, 14);
		frame.getContentPane().add(lblEmail);
		
		btnCreateNewMember = new JButton("Create new member");
		btnCreateNewMember.setBounds(92, 323, 301, 53);
		frame.getContentPane().add(btnCreateNewMember);
		
		btnBackToManipulate = new JButton("Back to manipulate members");
		btnBackToManipulate.setBounds(266, 11, 208, 23);
		frame.getContentPane().add(btnBackToManipulate);
	}
	
	public JFrame getFrame(){
		return frame;
	}
}
