package views;
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
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ManipulateMembers extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField textField;

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
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(290, 45, 173, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSearchFor = new JLabel("Search for the member with");
		lblSearchFor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSearchFor.setBounds(6, 48, 173, 14);
		frame.getContentPane().add(lblSearchFor);
		
		String[] searchFor = { "Author", "Name", "Barcode" };
		JComboBox comboBox = new JComboBox(searchFor);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Name", "Surname", "Username", "E-Mail"}));
		comboBox.setBounds(189, 45, 89, 20);
		frame.getContentPane().add(comboBox);
		
		JButton btnBackToLogin = new JButton("Back to main menu");
		btnBackToLogin.setBounds(322, 11, 152, 23);
		frame.getContentPane().add(btnBackToLogin);
		
		String[] foundBooks = { "Silmarillion, J.R.R. Tolkien, 1276312", "Hobbit, J.R.R. Tolkien, 1214312" };
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(334, 76, 89, 23);
		frame.getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 77, 237, 337);
		frame.getContentPane().add(scrollPane);	
		
		JList list = new JList(foundBooks);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Rahmi Berk, \u015Eefkatli, rahmi.sefkatli, berksefkatli@gmail.com", "Umut, \u00D6zt\u00FCrk, umut.ozturk, umut_ozturk@hacettepe.edu.tr", "\u00D6zlem, Demirta\u015F, ozlem.demirtas, ozlemdemirtas.pr@gmail.com"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JButton btnNewButton = new JButton("Edit member");
		btnNewButton.setBounds(311, 147, 142, 71);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCreateNewmember = new JButton("New member");
		btnCreateNewmember.setBounds(311, 354, 142, 60);
		frame.getContentPane().add(btnCreateNewmember);
		
		JButton btnDeletemember = new JButton("Delete member");
		btnDeletemember.setBounds(311, 253, 142, 71);
		frame.getContentPane().add(btnDeletemember);
		
		
		list.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		        if (!event.getValueIsAdjusting()){
		            JList source = (JList)event.getSource();
		            //source.remove(source.getSelectedIndex());
		            String selected = source.getSelectedValue().toString();
		            /*if(selected.equals("Silmarillion, J.R.R. Tolkien, 1276312")){
		            	textArea.setText("Chosen book is currently loaned to someone else, you may add your name to waiting list.");
		            	btnAddNameTo.setEnabled(true);
		            	btnSelfcheckout.setEnabled(false);
		            }
		            else{
		            	textArea.setText("Chosen book is available, you may proceed to checkout.");
		            	btnAddNameTo.setEnabled(false);
		            	btnSelfcheckout.setEnabled(true);
		            }*/
		            
		        }
		    }
		});
		
	}
	
	public JFrame getFrame(){
		return frame;
	}
}
