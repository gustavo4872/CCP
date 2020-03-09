package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mensagem.Mensagem;
import model.Servico;
import sql.Sql;
import util.Conexao;

public class DashboardController {
	
	@FXML TextField clienteTxf;
	@FXML TextField veiculoTxf;
	@FXML TextField servicoTxf;
	@FXML ProgressBar barraPgrb;
	
	@FXML TableView<Servico> tbl;
	@FXML TableColumn<Servico, String> colEntrada;
	@FXML TableColumn<Servico, String> colSaida;
	@FXML TableColumn<Servico, String> colVeiculo;
	@FXML TableColumn<Servico, String> colServico;
	@FXML TableColumn<Servico, Number> colTotal;
	
	private Servico servico;
	
	@FXML
	public void initialize() {
		inicializaTbl();
	}
		
	private void inicializaTbl() {
		tbl.getItems().clear();
		getDadosDB();
		colEntrada.setCellValueFactory(CellData -> CellData.getValue().entradaProperty());
		colSaida.setCellValueFactory(CellData -> CellData.getValue().saidaProperty());
		colVeiculo.setCellValueFactory(CellData -> CellData.getValue().getVeiculo().modeloProperty());
		colServico.setCellValueFactory(CellData -> CellData.getValue().getTipo().nomeProperty());
		colTotal.setCellValueFactory(CellData -> CellData.getValue().getTipo().precoProperty());
	}
	
	private void getDadosDB() {
		try {
			Connection conn = Conexao.getConnection();
			String sql = "Select * from SERVICO where STATUS = 0";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				servico = new Servico();
				servico.setId(rs.getInt("ID"));
				servico.setTipo(Sql.buscaTipo(rs.getInt("TIPO_ID")));
				//servico.setVeiculo(Sql.buscaVeiculo(rs.getInt("VEICULO_ID")));
				//servico.setEntrada(rs.getString("ENTRADA"));
				servico.setSaida(rs.getString("SAIDA"));
				tbl.getItems().add(servico);
			}
			conn.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void getLinha() {
		servico = tbl.getSelectionModel().getSelectedItem();
		if (!servico.equals(null)) {
			clienteTxf.setText(servico.getVeiculo().getCliente().getNome());
			veiculoTxf.setText(servico.getVeiculo().getModelo());
			servicoTxf.setText(servico.getTipo().getNome());
		}
	}
	
	@FXML
	public void finalizar() {
		servico = tbl.getSelectionModel().getSelectedItem();
		if (!servico.equals(null)) {
			Sql.finalizarServico(servico);
			tbl.getItems().remove(servico);
			limpar();
			Mensagem.confirmacao("Serviço Finalizado!");
		}
	}
	
	@FXML
	public void limpar() {
		clienteTxf.setText(null);
		veiculoTxf.setText(null);
		servicoTxf.setText(null);
		servico = null;
	}
	
	@FXML
	public void atualizar() {
		inicializaTbl();
	}
}