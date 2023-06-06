package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DAO;
import util.Validador;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Servicos extends JDialog {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pst;
	DAO dao = new DAO();

	private JTextField txtOS;
	private JTextField txtDate;
	private JTextField txtEquipamento;
	private JTextField txtDefeito;
	private JTextField txtValor;
	private JTextField txtID;
	private JButton btnEditar;
	private JButton btnAdicionar;
	private JButton btnExcluir;
	private JButton btnApagar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servicos dialog = new Servicos();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Servicos() {
		setTitle("Serviços");
		setBounds(100, 100, 602, 357);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("OS");
		lblNewLabel.setBounds(28, 24, 52, 20);
		getContentPane().add(lblNewLabel);

		txtOS = new JTextField();
		txtOS.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});

		txtOS.setBounds(90, 24, 107, 20);
		getContentPane().add(txtOS);
		txtOS.setColumns(10);
		txtOS.setDocument(new Validador(5));
		
		JLabel lblDatwe = new JLabel("Date");
		lblDatwe.setBounds(28, 55, 52, 20);
		getContentPane().add(lblDatwe);

		txtDate = new JTextField();
		txtDate.setEnabled(false);
		txtDate.setColumns(10);
		txtDate.setBounds(102, 55, 394, 20);
		getContentPane().add(txtDate);

		JLabel lblEquipamentio = new JLabel("Equipamento");
		lblEquipamentio.setBounds(28, 86, 97, 20);
		getContentPane().add(lblEquipamentio);

		txtEquipamento = new JTextField();
		txtEquipamento.setColumns(10);
		txtEquipamento.setBounds(135, 86, 361, 20);
		txtEquipamento.setDocument(new Validador(200));
		getContentPane().add(txtEquipamento);

		JLabel lblNewLabel_1_1 = new JLabel("Defeito");
		lblNewLabel_1_1.setBounds(28, 123, 52, 20);
		getContentPane().add(lblNewLabel_1_1);

		txtDefeito = new JTextField();
		txtDefeito.setColumns(10);
		txtDefeito.setBounds(90, 123, 406, 20);
		txtDefeito.setDocument(new Validador(200));
		getContentPane().add(txtDefeito);

		JLabel lblNewLabel_1_2 = new JLabel("valor");
		lblNewLabel_1_2.setBounds(28, 154, 52, 20);
		getContentPane().add(lblNewLabel_1_2);

		txtValor = new JTextField();
		txtValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});
		txtValor.setColumns(10);
		txtValor.setBounds(112, 154, 125, 20);
		txtOS.setDocument(new Validador(10));
		getContentPane().add(txtValor);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setBounds(48, 240, 97, 36);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setBounds(225, 240, 97, 36);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(396, 240, 97, 36);
		getContentPane().add(btnExcluir);

		txtID = new JTextField();
		txtID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});
		txtID.setColumns(10);
		txtID.setBounds(423, 24, 107, 20);
		txtID.setDocument(new Validador(5));
		getContentPane().add(txtID);

		JLabel lblId = new JLabel("ID do cliente");
		lblId.setBounds(325, 24, 88, 20);
		getContentPane().add(lblId);

		JButton btnBuscar = new JButton("Pesquisar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(207, 24, 97, 20);
		getContentPane().add(btnBuscar);

		btnApagar = new JButton("");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparcampos();
			}
		});
		btnApagar.setIcon(new ImageIcon(Servicos.class.getResource("/img/erase.png")));
		btnApagar.setBounds(500, 171, 64, 64);
		getContentPane().add(btnApagar);

	}

	public void limparcampos() {
		// Nulifica todos os campos
		txtOS.setText(null);
		txtID.setText(null);
		txtDate.setText(null);
		txtEquipamento.setText(null);
		txtDefeito.setText(null);
		txtValor.setText(null);
	}

	public void adicionar() {
		// teste do botão para limpar campos

		// Validação
		if (txtID.getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "O campo ID não pode estar vazio.");
			txtOS.requestFocus();
//		} else if (txtIDCli.getText().isEmpty()) {
//			JOptionPane.showInternalMessageDialog(null, "O campo IDCliente não pode estar vazio.");
//			txtIDCli.requestFocus();
		} else if (txtDate.getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "O campo date não pode estar vazio.");
			txtDate.requestFocus();
		} else if (txtEquipamento.getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "O campo Equipamento não pode estar vazio.");
			txtEquipamento.requestFocus();
		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "O campo Defeito não pode estar vazio.");
			txtDefeito.requestFocus();
		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "O campo Valor não pode estar vazio.");
			txtValor.requestFocus();
		} else
			try {
				con = dao.conectar();
				String comando = "insert into servicos (id,equipamento,defeito,valor) value (?,?,?,?);";
				// Começo da declaração
				pst = con.prepareStatement(comando);
				// Sequencia de definição dos valores string dentro do (?)
				pst.setString(1, txtID.getText());
				pst.setString(2, txtEquipamento.getText());
				pst.setString(3, txtDefeito.getText());
				pst.setString(4, txtValor.getText());
//				pst.setString(5, txtID.getText());

				btnEditar.setEnabled(true);
				con.close();
				limparcampos();
				JOptionPane.showInternalMessageDialog(null, "Adicionado com sucesso");
			} catch (SQLException se) {
				se.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
			}
	}//

	public void excluir() {

	}

	public void buscar() {

		String comando = "Select * into servicos where os = ? ";

		try {
			con = dao.conectar();
			pst = con.prepareStatement(comando);
			pst.setString(1, txtOS.getText());
			rs = pst.executeQuery();
			if (rs.next()) {
//			txtOS.setText(rs.getString(1));
				txtDate.setText(rs.getString(2));
				txtEquipamento.setText(rs.getString(3));
				txtDefeito.setText(rs.getString(4));
				txtValor.setText(rs.getString(5));
				txtID.setText(rs.getString(6));

//			OS
//			dataOS
//			equipame
//			defeito
//			valor
//			id

			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editar() {

	}

	public void onlyNum(KeyEvent e) {
		char c = e.getKeyChar();
		if (Character.isLetter(c)) {
			e.consume();

		}
	}
}
