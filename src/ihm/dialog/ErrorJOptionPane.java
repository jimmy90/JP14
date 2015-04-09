package ihm.dialog;

import javax.swing.JOptionPane;

public class ErrorJOptionPane extends JOptionPane {

	private static final long serialVersionUID = 1L;
	
	public ErrorJOptionPane(String title,String text) {
		// TODO Auto-generated constructor stub
		ErrorJOptionPane.showMessageDialog(null, text, title, JOptionPane.ERROR_MESSAGE);
	}

}
