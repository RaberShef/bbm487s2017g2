package views;

import javax.swing.JFrame;

public class NotificationView extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	
	public NotificationView() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Notification");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame.setResizable(false);
	}
	
	public JFrame getFrame(){
		return frame;
	}
}
