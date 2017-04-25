package views;

import javax.swing.JFrame;

public class PayFineView extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	
	public PayFineView() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Pay Fine");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame.setResizable(false);
	}
	
	public JFrame getFrame(){
		return frame;
	}
}
