package views;
import java.awt.EventQueue;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controllers.BookController;
import models.BookModel;

public class EditBook {

	private JFrame frmEditBook;
	private JTextField txtBookName;
	private JTextField txtAuthorName;
	private JLabel lblAuthorName;
	private JTextField txtCount;
	private JLabel lblCount;
	private JButton btnCreateNewUser;
	private JButton btnBackToManipulate;
	private JTextField txtNumberOfPages;
	private JTextField txtPrintingYear;
	private BookModel selectedBookModel=null;
	private JLabel label;
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditBook window = new EditBook();
					window.frmEditBook.setVisible(true);
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
	public EditBook(BookModel selectedBookModel) {
		this.selectedBookModel=selectedBookModel;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEditBook = new JFrame("Edit Book");
		frmEditBook.setBounds(100, 100, 500, 500);
		frmEditBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEditBook.getContentPane().setLayout(null);
		
		txtBookName = new JTextField();
		txtBookName.setText(selectedBookModel.getName());
		txtBookName.setToolTipText("");
		txtBookName.setBounds(222, 166, 134, 20);
		frmEditBook.getContentPane().add(txtBookName);
		txtBookName.setColumns(1);
		
		JLabel lblBookName = new JLabel("Name :");
		lblBookName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBookName.setBounds(137, 169, 75, 14);
		frmEditBook.getContentPane().add(lblBookName);
		
		txtAuthorName = new JTextField();
		txtAuthorName.setText(selectedBookModel.getAuthor_name());
		txtAuthorName.setToolTipText("");
		txtAuthorName.setColumns(1);
		txtAuthorName.setBounds(222, 190, 134, 20);
		frmEditBook.getContentPane().add(txtAuthorName);
		
		lblAuthorName = new JLabel("Author :");
		lblAuthorName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuthorName.setBounds(157, 193, 55, 14);
		frmEditBook.getContentPane().add(lblAuthorName);
		
		txtCount = new JTextField();
		txtCount.setText(selectedBookModel.getCount()+"");
		txtCount.setToolTipText("");
		txtCount.setColumns(1);
		txtCount.setBounds(222, 214, 134, 20);
		frmEditBook.getContentPane().add(txtCount);
		
		lblCount = new JLabel("Count :");
		lblCount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCount.setBounds(148, 217, 64, 14);
		frmEditBook.getContentPane().add(lblCount);
		
		
		
		btnBackToManipulate = new JButton("Back to manipulate books");
		btnBackToManipulate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ManipulateBooks manipulateBooks=new ManipulateBooks();
				JFrame frameManipulateBook=manipulateBooks.getFrame();
				frameManipulateBook.setVisible(true);
				frmEditBook.dispose();
				
			}
		});
		btnBackToManipulate.setBounds(286, 11, 188, 23);
		frmEditBook.getContentPane().add(btnBackToManipulate);
		
		JLabel lblNumberOfPages = new JLabel("Number of pages :");
		lblNumberOfPages.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumberOfPages.setBounds(108, 240, 104, 14);
		frmEditBook.getContentPane().add(lblNumberOfPages);
		
		txtNumberOfPages = new JTextField();
		txtNumberOfPages.setToolTipText("");
		txtNumberOfPages.setText(selectedBookModel.getNumber_of_pages()+"");
		txtNumberOfPages.setColumns(1);
		txtNumberOfPages.setBounds(222, 237, 134, 20);
		frmEditBook.getContentPane().add(txtNumberOfPages);
		
		JLabel lblPrintingYear = new JLabel("Printing year :");
		lblPrintingYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrintingYear.setBounds(118, 265, 94, 14);
		frmEditBook.getContentPane().add(lblPrintingYear);
		
		txtPrintingYear = new JTextField();
		txtPrintingYear.setToolTipText("");
		txtPrintingYear.setText(selectedBookModel.getPrinting_year()+"");
		txtPrintingYear.setColumns(1);
		txtPrintingYear.setBounds(222, 262, 134, 20);
		frmEditBook.getContentPane().add(txtPrintingYear);
		
		
		btnCreateNewUser = new JButton("Update book");
		btnCreateNewUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			 if((txtAuthorName.getText()!=null && txtAuthorName.getText().trim().length()>0)&&(txtBookName.getText()!=null&&txtBookName.getText().trim().length()>0)
			   && (txtCount.getText()!=null&&txtCount.getText().trim().length()>0)&&(txtNumberOfPages.getText()!=null&&txtNumberOfPages.getText().trim().length()>0)&&(txtPrintingYear.getText()!=null&&txtPrintingYear.getText().trim().length()>0)){
				 
				 selectedBookModel.setAuthor_name(txtAuthorName.getText());
				 selectedBookModel.setName(txtBookName.getText());
				 selectedBookModel.setCount(Integer.parseInt(txtCount.getText()));
				 selectedBookModel.setNumber_of_pages(Integer.parseInt(txtNumberOfPages.getText()));
				 selectedBookModel.setPrinting_year(Integer.parseInt(txtPrintingYear.getText()));
				 BookController.updateBook(selectedBookModel);
				 
				 ManipulateBooks manipulateBooks=new ManipulateBooks();
			     JFrame manipulateBooksForFrame=manipulateBooks.getFrame();
			     manipulateBooksForFrame.setVisible(true);
				 frmEditBook.dispose();
			 }
				
			}
		});
		btnCreateNewUser.setBounds(222, 289, 134, 31);
		frmEditBook.getContentPane().add(btnCreateNewUser);
		
		BufferedImage myPicture=null;
		try {
			myPicture = ImageIO.read(new File("resources/update.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		label = new JLabel(new ImageIcon(myPicture));
		label.setBounds(108, 45, 290, 110);
		frmEditBook.getContentPane().add(label);
	}
	public JFrame getFrame() {
		return frmEditBook;
	}
}
