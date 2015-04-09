package ihm.dialog;

import javax.swing.JOptionPane;

public class SuccessOptionJPane extends JOptionPane {

	private static final long serialVersionUID = 1L;
	
	public SuccessOptionJPane(String title,String text) {
		// TODO Auto-generated constructor stub
		SuccessOptionJPane.showMessageDialog(null, text, title, JOptionPane.INFORMATION_MESSAGE);
	}

}
