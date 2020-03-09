package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;
import sql.Sql;
import util.Conexao;

public class TabelaClienteController {
	
	@FXML TableView<Cliente> tbl;
	@FXML TableColumn<Cliente, String> colNome;
	@FXML TableColumn<Cliente, String> colContato;
	@FXML TableColumn<Cliente, String> colEmail;
	@FXML TableColumn<Cliente, String> colAnvr;
	@FXML TableColumn<Cliente, String> colCidade;
	@FXML TableColumn<Cliente, String> colEstado;
	@FXML TableColumn<Cliente, String> colComentario;

	@FXML TextField pesquisaTxf;
	@FXML ComboBox<String> campoCmBx;
	
	private Cliente cliente;
	
	@FXML
	public void initialize() {
		inicializaTbl();
		inicializaCmBxPesquisa();
	}
	
	private void inicializaCmBxPesquisa() {
		campoCmBx.getItems().add("Nome");
		campoCmBx.getItems().add("Contato");
		campoCmBx.getItems().add("Email");
		campoCmBx.getItems().add("Estado");
		campoCmBx.getItems().add("Cidade");
		campoCmBx.getItems().add("Comentario");
		campoCmBx.getSelectionModel().selectFirst();
	}
	
	private void inicializaTbl() {
		getDadosDB();
		colNome.setCellValueFactory(CellData -> CellData.getValue().nomeProperty());
		colContato.setCellValueFactory(CellData -> CellData.getValue().contatoProperty());
		colEmail.setCellValueFactory(CellData -> CellData.getValue().emailProperty());
		colAnvr.setCellValueFactory(CellData -> CellData.getValue().dataProperty());
		colCidade.setCellValueFactory(CellData -> CellData.getValue().getCidade().nomeProperty());
		colEstado.setCellValueFactory(CellData -> CellData.getValue().getCidade().getEstado().nomeProperty());
		colComentario.setCellValueFactory(CellData -> CellData.getValue().comentarioProperty());
	}
	
	private void getDadosDB() {
		try {
			Connection conn = Conexao.getConnection();
			String sql = "Select * from PROP";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getInt("ID"));
				cliente.setNome(rs.getString("NOME"));
				cliente.setEmail(rs.getString("EMAIL"));
				cliente.setContato(rs.getString("CONTATO"));
				cliente.setCidade(Sql.buscaCidade(rs.getInt("CIDADE_ID")));
				cliente.setComentario(rs.getString("COMENT"));
				cliente.setData(rs.getString("DT_ANVR"));
				tbl.getItems().add(cliente);
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
	
	@FXML
	public void editarCliente() {
		if (tbl.getSelectionModel().getSelectedItem()!=null) {
			EditarClienteController.cliente = tbl.getSelectionModel().getSelectedItem();
			try {
				Stage stage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("EditarCliente.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}