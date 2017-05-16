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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controllers.BookController;
import models.BookModel;
import javax.swing.JPanel;

public class CreateBook {

	private JFrame frame;
	private JTextField txtUsername;
	private JTextField txtAuthorName;
	private JLabel lblAuthorName;
	private JTextField txtcount;
	private JLabel lblCount;
	private JButton btnCreateNewUser;
	private JButton btnBackToManipulate;
	private JTextField txtnumberOfPages;
	private JTextField txtPrintingYear;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateBook window = new CreateBook();
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
		
		txtUsername = new JTextField();
		txtUsername.setToolTipText("");
		txtUsername.setBounds(222, 166, 182, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(1);
		
		JLabel lblUsername = new JLabel("Name :");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(137, 169, 75, 14);
		frame.getContentPane().add(lblUsername);
		
		txtAuthorName = new JTextField();
		txtAuthorName.setToolTipText("");
		txtAuthorName.setColumns(1);
		txtAuthorName.setBounds(222, 190, 182, 20);
		frame.getContentPane().add(txtAuthorName);
		
		lblAuthorName = new JLabel("Author :");
		lblAuthorName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuthorName.setBounds(157, 194, 55, 14);
		frame.getContentPane().add(lblAuthorName);
		
		txtcount = new JTextField();
		txtcount.setToolTipText("");
		txtcount.setColumns(1);
		txtcount.setBounds(222, 214, 182, 20);
		frame.getContentPane().add(txtcount);
		
		lblCount = new JLabel("Count:");
		lblCount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCount.setBounds(139, 217, 73, 14);
		frame.getContentPane().add(lblCount);
		
	
		btnBackToManipulate = new JButton("Back to manipulate books");
		btnBackToManipulate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ManipulateBooks manipulateBooks=new ManipulateBooks();
				JFrame frameManipulateBook=manipulateBooks.getFrame();
				frameManipulateBook.setVisible(true);
				frame.dispose();
				
			}
		});
		btnBackToManipulate.setBounds(286, 11, 188, 23);
		frame.getContentPane().add(btnBackToManipulate);
		
		JLabel lblNumberOfPages = new JLabel("Number of pages :");
		lblNumberOfPages.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumberOfPages.setBounds(108, 240, 104, 14);
		frame.getContentPane().add(lblNumberOfPages);
		
		txtnumberOfPages = new JTextField();
		txtnumberOfPages.setToolTipText("");
		txtnumberOfPages.setColumns(1);
		txtnumberOfPages.setBounds(222, 237, 182, 20);
		frame.getContentPane().add(txtnumberOfPages);
		
		JLabel lblPrintingYear = new JLabel("Printing year :");
		lblPrintingYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrintingYear.setBounds(118, 265, 94, 14);
		frame.getContentPane().add(lblPrintingYear);
		
		txtPrintingYear = new JTextField();
		txtPrintingYear.setToolTipText("");
		txtPrintingYear.setColumns(1);
		txtPrintingYear.setBounds(222, 262, 182, 20);
		frame.getContentPane().add(txtPrintingYear);
		
		
		
		
		btnCreateNewUser = new JButton("Create book");
		btnCreateNewUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if((txtAuthorName.getText()!=null&&txtAuthorName.getText().trim().length()>0)&& (txtcount.getText()!=null &&txtcount.getText().trim().length()>0)&&(txtnumberOfPages.getText()!=null &&txtnumberOfPages.getText().trim().length()>0)
				  &&(txtPrintingYear.getText()!=null && txtPrintingYear.getText().trim().length()>0)&&(txtUsername.getText()!=null&&txtUsername.getText().trim().length()>0)){
				  try{
					BookModel bookModel=new BookModel(0,txtUsername.getText(),txtAuthorName.getText(),Integer.parseInt(txtPrintingYear.getText()),Integer.parseInt(txtnumberOfPages.getText()),Integer.parseInt(txtcount.getText()));
					BookController.createNewBook(bookModel);
					
					ManipulateBooks manipulateBooks=new ManipulateBooks();
					JFrame manipulateBooksForFrame=manipulateBooks.getFrame();
					manipulateBooksForFrame.setVisible(true);
					frame.dispose();
				  }
				  catch(Exception ex){
					  JOptionPane.showMessageDialog(frame, "New book cannot be created!");
				  }
				}
				else{
					
					 JOptionPane.showMessageDialog(frame, "New book cannot be created!");
				}
				
			}
		});
		
		btnCreateNewUser.setBounds(222, 293, 183, 35);
		frame.getContentPane().add(btnCreateNewUser);
		
		
		BufferedImage myPicture=null;
		try {
			myPicture = ImageIO.read(new File("resources/create.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JLabel label = new JLabel(new ImageIcon(myPicture));
		label.setBounds(108, 45, 298, 110);
		frame.getContentPane().add(label);
		
		
		
		
		frame.setResizable(false);
	}
     
	public JFrame getFrame() {
		return frame;
	}
}
