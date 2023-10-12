package view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import model.DAO;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.UIManager;

import org.w3c.dom.css.Rect;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Relatorios extends JDialog {
	private static final long serialVersionUID = 1L;

	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

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

	// Dialog
	public Relatorios() {
		setResizable(false);
		setTitle("Relatórios");
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Relatorios.class.getResource("/img/ReportIcon.png")));
		setBounds(100, 100, 461, 207);
		getContentPane().setLayout(null);

		JButton btnServicos = new JButton("Servicos");
		btnServicos.setIcon(new ImageIcon(Relatorios.class.getResource("/img/ToolIcon.png")));
		btnServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			relatorioServicos();}
		});
		btnServicos.setBorder(UIManager.getBorder("ComboBox.editorBorder"));
		btnServicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnServicos.setToolTipText("Servicos");
		btnServicos.setBounds(246, 55, 137, 50);
		getContentPane().add(btnServicos);
		
		JButton btnClientes_1 = new JButton("Clientes");
		btnClientes_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes_1.setIcon(new ImageIcon(Relatorios.class.getResource("/img/clientes.png")));
		btnClientes_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			relatorioClientes();}
		});
		btnClientes_1.setToolTipText("Clientes");
		btnClientes_1.setBorder(UIManager.getBorder("ComboBox.editorBorder"));
		btnClientes_1.setBounds(44, 55, 137, 50);
		getContentPane().add(btnClientes_1);
		setLocationRelativeTo(null);
	}

	/**
	 * Impressão da OS
	 */
	
	private void relatorioClientes() {
		Document document = new Document();
		try {
			
			PdfWriter.getInstance(document, new FileOutputStream("clientes.pdf"));
			
			document.open();
			
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			
			document.add(new Paragraph("Clientes:"));
			document.add(new Paragraph(" ")); //pular uma linha
			
			String readClientes = "select nome,fone,cep,cpf from clientes order by nome";
			try {
				//abrir a conexão com o banco
				con = dao.conectar();
				//preparar a query (executar a instrução sql)
				pst = con.prepareStatement(readClientes);
				//obter o resultado (trazer do banco de dados)
				rs = pst.executeQuery();
				//atenção uso do while para trazer todos os clientes
				// Criar uma tabela de duas colunas usando o framework(itextPDF)
				PdfPTable tabela = new PdfPTable(4);
				
				PdfPCell col1 = new PdfPCell(new Paragraph("Cliente"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Telefone"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Cep"));
				PdfPCell col4 = new PdfPCell(new Paragraph("Cpf"));
				
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);
				
				while (rs.next()) {
					//popular a tabela
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
				}				
				//adicionar a tabela ao documento pdf
				document.add(tabela);
				//fechar a conexão com o banco
				con.close();
				
				Image imagem = Image.getInstance(Relatorios.class.getResource("/img/ConsoleIcon.png"));
				imagem.scaleToFit(128,128);
				imagem.setAbsolutePosition(350,750);
				document.add(imagem);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
		
		try {
			Desktop.getDesktop().open(new File("clientes.pdf"));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	//IMPRESSÃO DE RELATÓRIOS

		private void relatorioServicos() {
			//instanciar um objeto para construir a página pdf
			Document document = new Document();
			//configurar como A4 e modo paisagem
			document.setPageSize(PageSize.A4.rotate());
			//gerar o documento pdf
			try {
				//criar um documento em branco (pdf) de nome clientes.pdf
				PdfWriter.getInstance(document, new FileOutputStream("servicos.pdf"));
				//abrir o documento (formatar e inserir o conteúdo)
				document.open();
				//adicionar a data atual
				Date dataRelatorio = new Date();
				DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
				
				document.add(new Paragraph(formatador.format(dataRelatorio)));
				//adicionar um páragrafo
				document.add(new Paragraph("Equipamento console:"));
				document.add(new Paragraph(" ")); //pular uma linha
				//----------------------------------------------------------
				//query (instrução sql para gerar o relatório de clientes)
				
				String readServicos = "select os,dataOS,equipamento,defeito,valor,nome from servicos inner join clientes on servicos.id = clientes.idcli";
				try {
					//abrir a conexão com o banco
					
					con = dao.conectar();
					//preparar a query (executar a instrução sql)	
					pst = con.prepareStatement(readServicos);
					//obter o resultado (trazer do banco de dados)
					rs = pst.executeQuery();
					//atenção uso do while para trazer todos os clientes
					// Criar uma tabela de duas colunas usando o framework(itextPDF)
					PdfPTable tabela = new PdfPTable(6); //(6) número de colunas
					// Criar o cabeçalho da tabela
					
					
					
					PdfPCell col1 = new PdfPCell(new Paragraph("OS"));
					PdfPCell col2 = new PdfPCell(new Paragraph("Data"));
					PdfPCell col3 = new PdfPCell(new Paragraph("Equipamento"));
					PdfPCell col4 = new PdfPCell(new Paragraph("Defeito"));
					PdfPCell col5 = new PdfPCell(new Paragraph("Valor"));
					PdfPCell col6 = new PdfPCell(new Paragraph("Clientes"));
					
					tabela.addCell(col1);
					tabela.addCell(col2);
					tabela.addCell(col3);
					tabela.addCell(col4);
					tabela.addCell(col5);
					tabela.addCell(col6);
					
					while (rs.next()) {
						//popular a tabela
						tabela.addCell(rs.getString(1));
						tabela.addCell(rs.getString(2));
						tabela.addCell(rs.getString(3));
						tabela.addCell(rs.getString(4));
						tabela.addCell(rs.getString(5));
						tabela.addCell(rs.getString(6));
						
						Image imagem = Image.getInstance(Relatorios.class.getResource("/img/ConsoleIcon.png"));
						imagem.scaleToFit(128,128);
						imagem.setAbsolutePosition(20,20);
						document.add(imagem);
					}				
					//adicionar a tabela ao documento pdf
					document.add(tabela);
					//fechar a conexão com o banco
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//fechar o documento (pronto para "impressão" (exibir o pdf))
			document.close();
			//Abrir o desktop do sistema operacional e usar o leitor padrão
			//de pdf para exibir o documento
			try {
				Desktop.getDesktop().open(new File("servicos.pdf"));
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}// FIM DO RELATÓRIO CLIENTES
}//fim do construtor

