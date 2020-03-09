package application;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SampleController {
	
	@FXML private Label nomeLabel;
	@FXML private Label mediaALabel;
	@FXML private Label mediaRLabel;
	
	@FXML private TextField alunoTxf;
	@FXML private TextField disciplinaTxf;
	@FXML private TextField nota1Txf;
	@FXML private TextField nota2Txf;
	@FXML private TextField nota3Txf;
	
	@FXML private TableView<String> tbl;
	@FXML private TableColumn<String, String> colNome;
	@FXML private TableColumn<String, String> colDisciplina;
	@FXML private TableColumn<String, String> colMedia;
	@FXML private TableColumn<String, String> colSituacao;
	
	@FXML
	public void initialize() {
		lerArquivo();
	}
	
	private void lerArquivo() {
		Properties prop = new Properties();
		try (FileReader fr = new FileReader("conf.properties")) {
			prop.load(fr);
			nomeLabel.setText(prop.getProperty("nome"));
			mediaALabel.setText(prop.getProperty("mediaA"));
			mediaRLabel.setText(prop.getProperty("mediaR"));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String formataDados(String dado){
		   return dado.replaceAll("[^0-9]+", "");
	}
	
	@FXML 
	public void inserir() {
		
		String nota1 = zerosNota(formataDados(nota1Txf.getText()));
		String nota2 = zerosNota(formataDados(nota2Txf.getText()));
		String nota3 = zerosNota(formataDados(nota3Txf.getText()));
		
		try {
			String linha = "";
			
			String nomeString = String.format("%-20s", alunoTxf.getText());
			if (nomeString.length()>20) {
				char[] arrayAux = nomeString.toCharArray();
				arrayAux[19] = '.';
				for (int i = 0; i < 20; i++) {
					linha+=arrayAux[i];
				}
			}else {
				linha = nomeString;
			}
			String discString = String.format("%-15s", disciplinaTxf.getText());
			if (discString.length()>15) {
				char[] arrayAux = discString.toCharArray();
				arrayAux[14] = '.';
				for (int i = 0; i < 15; i++) {
					linha+=arrayAux[i];
				}
			}else {
				linha += discString;
			}
			
			linha+=nota1+nota2+nota3; 
			FileWriter filew = new FileWriter("dados.txt",true);
			BufferedWriter bufferedw = new BufferedWriter(filew);
			bufferedw.newLine();
			bufferedw.append(linha);			
			bufferedw.close();	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getCause());
		}
	}
	
	public String zerosNota(String nota) {
		String retorno = "";
		if (nota.length()==1) {
			retorno = "000"+nota;
		}else if (nota.length()==2) {
			retorno = "00"+nota;
		}else if (nota.length()==3) {
			retorno = "0"+nota;
		}else if (nota.length()==4) {
			retorno = nota;
		}
		return retorno;
	}
	
}