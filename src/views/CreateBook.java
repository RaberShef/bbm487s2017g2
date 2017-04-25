package views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CreateBook extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField txtBookName;
	private JTextField txtAuthor;
	private JLabel lblAuthor;
	private JTextField txtBarcode;
	private JLabel lblBarcode;
	private JButton btnCreateBook;
	private JButton btnBackToManipulate;
	private JTextField txtPages;
	private JTextField txtYear;
	private JLabel lblPrintingYear;
	private JLabel lblNumberOfPages;
	private JLabel lblBookName;

	/**
	 * Create the application.
	 */
	public CreateBook() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Create Book");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtBookName = new JTextField();
		txtBookName.setToolTipText("");
		txtBookName.setBounds(222, 166, 120, 20);
		frame.getContentPane().add(txtBookName);
		txtBookName.setColumns(1);
		
		lblBookName = new JLabel("Name :");
		lblBookName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBookName.setBounds(137, 169, 75, 14);
		frame.getContentPane().add(lblBookName);
		
		txtAuthor = new JTextField();
		txtAuthor.setToolTipText("");
		txtAuthor.setColumns(1);
		txtAuthor.setBounds(222, 190, 120, 20);
		frame.getContentPane().add(txtAuthor);
		
		lblAuthor = new JLabel("Author :");
		lblAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuthor.setBounds(157, 193, 55, 14);
		frame.getContentPane().add(lblAuthor);
		
		txtBarcode = new JTextField();
		txtBarcode.setToolTipText("");
		txtBarcode.setColumns(1);
		txtBarcode.setBounds(222, 214, 120, 20);
		frame.getContentPane().add(txtBarcode);
		
		lblBarcode = new JLabel("Barcode :");
		lblBarcode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBarcode.setBounds(148, 217, 64, 14);
		frame.getContentPane().add(lblBarcode);
		
		btnCreateBook = new JButton("Create book");
		btnCreateBook.setBounds(92, 323, 301, 53);
		frame.getContentPane().add(btnCreateBook);
		
		btnBackToManipulate = new JButton("Back to manipulate books");
		btnBackToManipulate.setBounds(286, 11, 188, 23);
		frame.getContentPane().add(btnBackToManipulate);
		
		lblNumberOfPages = new JLabel("Number of pages :");
		lblNumberOfPages.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumberOfPages.setBounds(108, 240, 104, 14);
		frame.getContentPane().add(lblNumberOfPages);
		
		txtPages = new JTextField();
		txtPages.setToolTipText("");
		txtPages.setColumns(1);
		txtPages.setBounds(222, 237, 120, 20);
		frame.getContentPane().add(txtPages);
		
		lblPrintingYear = new JLabel("Printing year :");
		lblPrintingYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrintingYear.setBounds(118, 265, 94, 14);
		frame.getContentPane().add(lblPrintingYear);
		
		txtYear = new JTextField();
		txtYear.setToolTipText("");
		txtYear.setColumns(1);
		txtYear.setBounds(222, 262, 120, 20);
		frame.getContentPane().add(txtYear);
		frame.setResizable(false);
	}

	public JFrame getFrame(){
		return frame;
	}
}
