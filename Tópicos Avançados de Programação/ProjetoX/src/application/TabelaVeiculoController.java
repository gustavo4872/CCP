package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Veiculo;
import sql.Sql;
import util.Conexao;

public class TabelaVeiculoController {
	
	@FXML TableView<Veiculo> tbl;
	@FXML TableColumn<Veiculo, String> colCliente;
	@FXML TableColumn<Veiculo, String> colModelo;
	@FXML TableColumn<Veiculo, String> colCor;
	@FXML TableColumn<Veiculo, String> colEstado;
	@FXML TableColumn<Veiculo, String> colCidade;
	@FXML TableColumn<Veiculo, String> colComentario;

	@FXML TextField pesquisaTxf;
	@FXML ComboBox<String> campoCmBx;
	
	private Veiculo veiculo;
	
	@FXML
	public void initialize() {
		inicializaTbl();
		inicializaCmBx();
	}
	
	private void inicializaCmBx() {
		campoCmBx.getItems().add("Cliente");
		campoCmBx.getItems().add("Modelo");
		campoCmBx.getItems().add("Cor");
		campoCmBx.getItems().add("Estado");
		campoCmBx.getItems().add("Cidade");
		campoCmBx.getItems().add("Comentario");
		campoCmBx.getSelectionModel().selectFirst();
	}
	
	private void inicializaTbl() {
		getDadosDB();
		colCliente.setCellValueFactory(CellData -> CellData.getValue().getCliente().nomeProperty());
		colModelo.setCellValueFactory(CellData -> CellData.getValue().modeloProperty());
		colCor.setCellValueFactory(CellData -> CellData.getValue().getCor().nomeProperty());
		colCidade.setCellValueFactory(CellData -> CellData.getValue().getCidade().nomeProperty());
		colEstado.setCellValueFactory(CellData -> CellData.getValue().getCidade().getEstado().nomeProperty());
		colComentario.setCellValueFactory(CellData -> CellData.getValue().comentarioProperty());
	}

	private void getDadosDB() {
		try {
			Connection conn = Conexao.getConnection();
			String sql = "Select * from VEICULO";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				veiculo = new Veiculo();
				veiculo.setId(rs.getInt("ID"));
				veiculo.setCliente(Sql.buscaCliente(rs.getInt("PROP_ID")));
				veiculo.setModelo(rs.getString("MODELO"));
				veiculo.setCor(Sql.buscaCor(rs.getInt("COR_ID")));
				veiculo.setCidade(Sql.buscaCidade(rs.getInt("CIDADE_ID")));
				veiculo.setComentario(rs.getString("COMENT"));
				tbl.getItems().add(veiculo);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void atualizaTabela() {
		tbl.getItems().clear();
		inicializaTbl();
	}
	
}
