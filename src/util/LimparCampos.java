package util;

import java.awt.Color;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class LimparCampos {
	private List<JTextField> listTxt;
	private List<JComboBox> listCB;
	
	public LimparCampos(List<JTextField> listTxt, List<JComboBox> listCB) {
		super();
		this.listTxt = listTxt;
		this.listCB = listCB;
	}

	public void clear(List<JTextField> listTxt) {
		for (JTextField obj : listTxt) {
			obj.setText(null);
			obj.setBackground(Color.WHITE);
		}
	}

	public void clear(List<JTextField> listTxt, List<JComboBox> listCB) {
		for (JTextField obj : listTxt) {
			obj.setText(null);
			obj.setBackground(Color.WHITE);
		}
		for (JComboBox obj : listCB) {
			obj.setSelectedItem("");
		}
	}
}
