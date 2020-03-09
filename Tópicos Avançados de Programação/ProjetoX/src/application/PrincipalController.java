package application;

import java.io.FileReader;
import java.util.Properties;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import model.Hora;

public class PrincipalController {
	
	@FXML TabPane tabPane;
	@FXML Label nomeLabel;
	@FXML Label horaLabel;
	
	public void initialize() {
		Properties prop = new Properties();
		try (FileReader fr = new FileReader("conf.properties")) {
			prop.load(fr);
			nomeLabel.setText(prop.getProperty("nome"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		Hora hora = new Hora(this.horaLabel);
		new Thread(hora).start();
		abreTelaPrincipal();
	}

	private Tab tabAberta(String titulo) {
		for (Tab tb : tabPane.getTabs()) {
			if (!(tb.getText()==null) && tb.getText().equals(titulo)) {
				return tb;
			}
		}
		return null;
	}
	
	private void selecionaTab(Tab tab) {
		tabPane.getSelectionModel().select(tab);
	}
	
	private void abreTab(String titulo, String path) {
		try {
			Tab tab = tabAberta(titulo);
			if (tab==null) {
				tab = new Tab(titulo);
				tab.setClosable(true);
				tabPane.getTabs().add(tab);
				tab.setContent((Node) FXMLLoader.load(getClass().getResource(path)));
			}			
			selecionaTab(tab);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void abreTelaCadastroCliente() {
		abreTab("Cadastro", "Cadastro.fxml");
	}
	
	@FXML
	public void abreTelaPrincipal() {
		abreTab("Dashboard", "Dashboard.fxml");
	}
	
	@FXML
	public void abreTelaDados() {
		abreTab("Estatísticas", "Estatistica.fxml");
	}

	@FXML
	public void abreTabelaCliente() {
		abreTab("TabelaCliente", "TabelaCliente.fxml");
	}
	
	@FXML
	public void abreTabelaVeiculo() {
		abreTab("TabelaVeiculo", "TabelaVeiculo.fxml");
	}
	
	@FXML
	public void abreTabelaServico() {
		abreTab("TabelaServico", "TabelaServico.fxml");
	}

}
