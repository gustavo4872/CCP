package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Cidade;
import model.Cliente;
import model.Cor;
import model.Estado;
import model.Servico;
import model.Tipo;
import model.Veiculo;
import util.Conexao;

public class Sql {
	
	private static Connection conn;
	private static String sql;
	private static PreparedStatement ps; 
	
	public static boolean cadastraCliente(TextField nome, TextField ddd, TextField numero, TextField email, ComboBox<Cidade> cidade, TextArea comentario, DatePicker dataDtPkC) {
		boolean cadastrado = true;
		try {
			conn = Conexao.getConnection();
			sql = "insert into PROP (NOME, CONTATO, EMAIL, DT_ANVR, CIDADE_ID, COMENT) values (?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, nome.getText());
			ps.setString(2, ddd.getText()+numero.getText());
			ps.setString(3, email.getText());
			ps.setString(4, (dataDtPkC.getValue()==null) ? null : dataDtPkC.getValue().toString());
			ps.setInt	(5, cidade.getValue().getId());
			ps.setString(6, comentario.getText());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			cadastrado = false;
		}
		return cadastrado;
	}
	
	public static boolean editarCliente(TextField nome, TextField ddd, TextField numero, TextField email, ComboBox<Cidade> cidade, TextArea comentario, DatePicker dataDtPkC, Cliente cliente) {
		boolean cadastrado = true;
		try {
			conn = Conexao.getConnection();
			sql = "update PROP set NOME = ?, CONTATO = ?, EMAIL = ?, DT_ANVR = ?, CIDADE_ID = ?, COMENT = ? where ID = "+cliente.getId();
			ps = conn.prepareStatement(sql);
			ps.setString(1, nome.getText());
			ps.setString(2, ddd.getText()+numero.getText());
			ps.setString(3, email.getText());
			ps.setString(4, (dataDtPkC.getValue()==null) ? null : dataDtPkC.getValue().toString());
			ps.setInt	(5, cidade.getValue().getId());
			ps.setString(6, comentario.getText());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			cadastrado = false;
		}
		return cadastrado;
	}
	
	public static boolean excluirCliente(Cliente cliente) {
		boolean cadastrado = true;
		try {
			conn = Conexao.getConnection();
			sql = "delete from PROP where ID = "+cliente.getId();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			cadastrado = false;
		}
		return cadastrado;
	}
	
	public static boolean cadastraVeiculo(ComboBox<Cliente> cliente, TextField modelo, TextField placa, ComboBox<Cor> cor, ComboBox<Cidade> cidade, TextArea comentario) {
		boolean cadastrado = true;
		try {
			conn = Conexao.getConnection();
			sql = "insert into VEICULO (MODELO, PROP_ID, PLACA, COR_ID, CIDADE_ID, COMENT) values (?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, modelo.getText());
			ps.setInt	(2, cliente.getValue().getId());
			ps.setString(3, placa.getText());
			ps.setInt	(4, cor.getValue().getId());
			ps.setInt	(5, cidade.getValue().getId());
			ps.setString(6, comentario.getText());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			cadastrado = false;
		}
		return cadastrado;
	}
	
	public static boolean cadastraServico(ComboBox<Veiculo> veiculo, ComboBox<Tipo> tipo, ComboBox<String> horaEntrada, 
			ComboBox<String> minutoEntrada, ComboBox<String> horaSaida, ComboBox<String> minutoSaida, TextArea comentario) {
		boolean cadastrado = true;
		try {
			conn = Conexao.getConnection();
			sql = "insert into SERVICO (VEICULO_ID, TIPO_ID, ENTRADA, SAIDA, COMENT, STATUS) values (?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt   (1, veiculo.getValue().getId());
			ps.setInt	(2, tipo.getValue().getId());
			ps.setString(3, horaEntrada.getSelectionModel().getSelectedItem()+minutoEntrada.getSelectionModel().getSelectedItem());
			ps.setString(4, horaSaida.getSelectionModel().getSelectedItem()+minutoSaida.getSelectionModel().getSelectedItem());
			ps.setString(5, comentario.getText());
			ps.setBoolean(6, false);
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			cadastrado = false;
		}
		return cadastrado;
	}
	
	public static Cliente buscaCliente(int id) {
		Cliente cliente = null;
		try {
			Connection conn = Conexao.getConnection();
			String sql = "Select * from PROP where ID = "+id;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			cliente = new Cliente();
			cliente.setId(rs.getInt("ID"));
			cliente.setNome(rs.getString("NOME"));
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cliente;
	}
	
	public static Veiculo buscaVeiculo(int id) {
		Veiculo veiculo = null;
		try {
			Connection conn = Conexao.getConnection();
			String sql = "Select * from VEICULO where ID = "+id;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			veiculo = new Veiculo();
			veiculo.setId(rs.getInt("ID"));
			veiculo.setModelo(rs.getString("MODELO"));
			veiculo.setCor(buscaCor(rs.getInt("COR_ID")));
			veiculo.setPlaca(rs.getString("PLACA"));
			veiculo.setCliente(buscaCliente(rs.getInt("PROP_ID")));
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return veiculo;
	}
	
	public static Cidade buscaCidade(int id) {
		Cidade cidade = null;
		try {
			Connection conn = Conexao.getConnection();
			String sql = "Select * from CIDADE where ID = "+id;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			cidade = new Cidade();
			cidade.setId(rs.getInt("ID"));
			cidade.setNome(rs.getString("NOME"));
			cidade.setEstado(buscaEstado(rs.getInt("UF_ID")));
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cidade;
	}
	
	public static Estado buscaEstado(int id) {
		Estado estado = null;
		try {
			Connection conn = Conexao.getConnection();
			String sql = "Select * from UF where ID = "+id;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			estado = new Estado();
			estado.setId(rs.getInt("ID"));
			estado.setNome(rs.getString("NOME"));
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}
	
	
	
	public static Cor buscaCor(int id) {
		Cor cor = null;
		try {
			Connection conn = Conexao.getConnection();
			String sql = "Select * from COR where ID = "+id;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			cor = new Cor();
			cor.setId(rs.getInt("ID"));
			cor.setNome(rs.getString("NOME"));
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cor;
	}
	
	
	public static Tipo buscaTipo(int id) {
		Tipo tipo = null;
		try {
			Connection conn = Conexao.getConnection();
			String sql = "Select * from TIPO where ID = "+id;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			tipo = new Tipo();
			tipo.setId(rs.getInt("ID"));
			tipo.setNome(rs.getString("NOME"));
			tipo.setPreco(rs.getDouble("PRECO"));
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipo;
	}
	
	public static boolean finalizarServico(Servico servico) {
		boolean cadastrado = true;
		try {
			conn = Conexao.getConnection();
			sql = "update SERVICO set STATUS = 1 where ID = "+servico.getId();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			cadastrado = false;
		}
		return cadastrado;
	}
	
}