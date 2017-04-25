package views;
import java.awt.EventQueue;

import javax.swing.AbstractListModel;
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

public class ManipulateBooks extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
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
		frame = new JFrame("Manipulate Books");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(290, 45, 173, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSearchFor = new JLabel("Search for the book with");
		lblSearchFor.setBounds(41, 48, 150, 14);
		frame.getContentPane().add(lblSearchFor);
		
		String[] searchFor = { "Author", "Name", "Barcode", "Page Count", "Printing Year" };
		JComboBox comboBox = new JComboBox(searchFor);
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
			String[] values = new String[] {"Silmarillion, J.R.R. Tolkien, 1276312, 500, 2000", "Hobbit, J.R.R. Tolkien, 1214312, 150, 1990"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JButton btnNewButton = new JButton("Edit book");
		btnNewButton.setBounds(311, 147, 130, 71);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCreateNewUser = new JButton("Create new book");
		btnCreateNewUser.setBounds(311, 354, 130, 60);
		frame.getContentPane().add(btnCreateNewUser);
		
		JButton btnDeleteUser = new JButton("Delete book");
		btnDeleteUser.setBounds(311, 253, 130, 71);
		frame.getContentPane().add(btnDeleteUser);
		
		
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

}
