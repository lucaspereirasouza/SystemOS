package util;

import java.awt.Color;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class JListTextValidate {
	boolean isFilled = false;
	boolean isFilledtxt = true;
	boolean isFilledcb = true;

	private List<JTextField> JListtxt;
	private List<JComboBox> JlistCb;

	public JListTextValidate(List<JTextField> jListtxt, List<JComboBox> jlistCb) {
		super();
		JListtxt = jListtxt;
		JlistCb = jlistCb;
	}

	public boolean IsEmpty(List<JTextField> JListtxt) {
		for (JTextField obj : JListtxt) {
			if (obj.getText().isEmpty()  || obj.getText().isBlank()) {
				System.out.println("vazio");
				obj.setBackground(Color.YELLOW);
				isFilled = false;
//				break;
			} else {
				System.out.println("Preenchido");
			}
		}

		System.out.println("---");
		System.out.println("once");
		System.out.println(isFilled);
		return isFilled;
	}

	public boolean IsEmpty(List<JTextField> JListtxt, List<JComboBox> JlistCb) {
		for (JTextField obj : JListtxt) {
			if (obj.getText().isEmpty() || obj.getText().isBlank()) {
				System.out.println("vazio");
				obj.setBackground(Color.YELLOW);
				isFilledcb = false;
//				break;
			} else {
				System.out.println("Preenchido");
				
			}
		}

		for (JComboBox obj : JlistCb) {
			if (obj.getSelectedItem().equals("")) {
				System.out.println("vazio");
				isFilledtxt = false;
				break;
			} else {
				System.out.println("Preenchido");
			}
		}

		if (isFilledtxt && isFilledcb) {
			isFilled = true;
			System.out.println("Preenchido");
		} else {
			isFilled = false;
			JOptionPane.showMessageDialog(null, "Por favor preencha todos os campos");
			System.out.println("Não preenchido");
		}
		System.out.println("---");
		System.out.println("once");
		return isFilled;
	}
}
