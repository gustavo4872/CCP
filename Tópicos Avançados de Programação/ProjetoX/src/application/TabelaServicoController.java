package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Servico;
import sql.Sql;
import util.Conexao;

public class TabelaServicoController {
	
	
	@FXML TableView<Servico> tbl;
	@FXML TableColumn<Servico, String> colVeiculo;
	@FXML TableColumn<Servico, String> colTipo;
	@FXML TableColumn<Servico, String> colEntrada;
	@FXML TableColumn<Servico, String> colPrevisao;
	@FXML TableColumn<Servico, String> colDesconto;
	@FXML TableColumn<Servico, Number> colTotal;
	@FXML TableColumn<Servico, String> colComentario;

	@FXML TextField pesquisaTxf;
	@FXML ComboBox<String> campoCmBx;
	
	private Servico servico;
	
	@FXML
	public void initialize() {
		inicializaTbl();
		inicializaCmBx();
	}
	
	private void inicializaCmBx() {
		campoCmBx.getItems().add("Modelo");
		campoCmBx.getItems().add("Tipo");
		campoCmBx.getItems().add("Entrada");
		campoCmBx.getItems().add("Previsão");
		campoCmBx.getItems().add("Desconto");
		campoCmBx.getItems().add("Comentário");
		campoCmBx.getSelectionModel().selectFirst();
	}
	
	private void inicializaTbl() {
		getDadosDB();
		colVeiculo.setCellValueFactory(CellData -> CellData.getValue().getVeiculo().modeloProperty());
		colTipo.setCellValueFactory(CellData -> CellData.getValue().getTipo().nomeProperty());
		colEntrada.setCellValueFactory(CellData -> CellData.getValue().entradaProperty());
		colPrevisao.setCellValueFactory(CellData -> CellData.getValue().saidaProperty());
		colTotal.setCellValueFactory(CellData -> CellData.getValue().getTipo().precoProperty());
	}
	
	private void getDadosDB() {
		try {
			Connection conn = Conexao.getConnection();
			String sql = "Select * from SERVICO";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				servico = new Servico();
				servico.setId(rs.getInt("ID"));
				servico.setVeiculo(Sql.buscaVeiculo(rs.getInt("VEICULO_ID")));
				servico.setTipo(Sql.buscaTipo(rs.getInt("TIPO_ID")));
				servico.setEntrada(rs.getString("ENTRADA"));
				servico.setSaida(rs.getString("SAIDA"));				
				tbl.getItems().add(servico);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
