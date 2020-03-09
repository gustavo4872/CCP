package comboBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.control.ComboBox;
import model.Cliente;
import util.Conexao;

public class ClienteCmBx {
	
	public static void preencheComboBox(ComboBox<Cliente> clienteV, ComboBox<Cliente> clienteS) {
		clienteV.getItems().clear();
		clienteS.getItems().clear();
		try {
			Connection conn = Conexao.getConnection();
			String sql = "Select * from PROP";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("ID"));
				cliente.setNome(rs.getString("NOME"));
				clienteV.getItems().add(cliente);
				clienteS.getItems().add(cliente);
			}
			clienteV.getSelectionModel().selectFirst();
			clienteS.getSelectionModel().selectFirst();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
