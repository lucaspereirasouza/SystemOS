package util;

import java.awt.Color;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class JListTextValidate {
	boolean isFilled = false;
	boolean isFilledtxt = false;
	boolean isFilledcb = false;

	private List<JTextField> JListtxt;
	private List<JComboBox> JlistCb;

	public JListTextValidate(List<JTextField> jListtxt, List<JComboBox> jlistCb) {
		super();
		JListtxt = jListtxt;
		JlistCb = jlistCb;
	}

	public boolean IsEmpty(List<JTextField> JListtxt) {
		for (JTextField obj : JListtxt) {
			if (obj.getText().isEmpty() || obj.getText().equals("")) {
				System.out.println("vazio");
				obj.setBackground(Color.YELLOW);
				isFilled = false;
//				break;
			} else {
				System.out.println("Preenchido");
				isFilled = true;
			}
		}

		System.out.println("---");
		System.out.println("once");
		System.out.println(isFilled);
		return isFilled;
	}

	public boolean IsEmpty(List<JTextField> JListtxt, List<JComboBox> JlistCb) {
		for (JTextField obj : JListtxt) {
			if (obj.getText().isEmpty() || obj.getText().equals("")) {
				System.out.println("vazio");
				obj.setBackground(Color.YELLOW);
				isFilledcb = false;
//				break;
			} else {
				System.out.println("Preenchido");
				isFilledcb = true;
			}
		}

		for (JComboBox obj : JlistCb) {
			if (obj.getSelectedItem().equals("")) {
				System.out.println("vazio");
				isFilledtxt = false;
				break;
			} else {
				System.out.println("Preenchido");
				isFilledtxt = true;
			}
		}

		if (isFilledtxt && isFilledcb) {
			isFilled = true;
		} else {

			JOptionPane.showMessageDialog(null, "Por favor preencha todos os campos");
			isFilled = false;
		}
		System.out.println("---");
		System.out.println("once");
		System.out.println(isFilled);
		return isFilled;
	}
}
