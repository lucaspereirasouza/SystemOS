package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.Cursor;
import javax.swing.UIManager;

public class Relatorios extends JDialog {
	private static final long serialVersionUID = 1L;
	// Aplication launch
	public static void main(String[] args) {
		try {
			Relatorios dialog = new Relatorios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Dialog
	public Relatorios() {
		setResizable(false);
		setTitle("Relatorio");
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Relatorios.class.getResource("/img/ReportIcon.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.setBorder(UIManager.getBorder("ComboBox.editorBorder"));
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setToolTipText("Clientes");
		btnClientes.setBounds(64, 115, 89, 23);
		getContentPane().add(btnClientes);
		
		JButton btnServicos = new JButton("Servicos");
		btnServicos.setBorder(UIManager.getBorder("ComboBox.editorBorder"));
		btnServicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnServicos.setToolTipText("Servicos");
		btnServicos.setBounds(252, 115, 89, 23);
		getContentPane().add(btnServicos);
	}
}
